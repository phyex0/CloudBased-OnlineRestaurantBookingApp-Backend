package com.upspoon.stock.service;

import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.common.enums.OrderStatus;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.stock.mapper.OrderToStockMapper;
import com.upspoon.stock.mapper.StockToPaymentMapper;
import com.upspoon.stock.model.Stock;
import com.upspoon.stock.model.StockTransaction;
import com.upspoon.stock.producer.KafkaProducer;
import com.upspoon.stock.repository.StockRepository;
import com.upspoon.stock.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author burak.yesildal
 */

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final OrderToStockMapper orderToStockMapper;
    private final StockTransactionRepository stockTransactionRepository;
    private final StockToPaymentMapper stockToPaymentMapper;

    @Autowired
    private KafkaProducer kafkaProducer;

    public StockServiceImpl(StockRepository stockRepository, OrderToStockMapper orderToStockMapper, StockTransactionRepository stockTransactionRepository, StockToPaymentMapper stockToPaymentMapper) {
        this.stockRepository = stockRepository;
        this.orderToStockMapper = orderToStockMapper;
        this.stockTransactionRepository = stockTransactionRepository;
        this.stockToPaymentMapper = stockToPaymentMapper;
    }


    @Transactional
    @Override
    public void save(OrderToStock orderToStock) {
        var stocks = stockRepository.findAllByProductIdIn(new ArrayList<>(orderToStock.getProductMap().keySet()));


        AtomicReference<Boolean> invalidStock = new AtomicReference<>(Boolean.FALSE);
        stocks.forEach(stock -> {
            if (orderToStock.getProductMap().containsKey(stock.getProductId())) {
                if (stock.getCount() - orderToStock.getProductMap().get(stock.getProductId()) >= 0)
                    stock.setCount(stock.getCount() - orderToStock.getProductMap().get(stock.getProductId()));
                else
                    invalidStock.set(Boolean.TRUE);
            }
        });

        if (invalidStock.get()) {
            //TODO: orderService doesn't have listener for the following event!
            orderToStock.setOrderStatus(OrderStatus.INVALID_STOCK);
            kafkaProducer.produceStockFailedEvent(orderToStock);
            return;
        }

        StockTransaction stockTransaction = orderToStockMapper.toEntity(orderToStock);
        StockToPayment stockToPayment = stockToPaymentMapper.toEntity(orderToStock);

        stockTransactionRepository.save(stockTransaction);
        stockRepository.saveAll(stocks);
        kafkaProducer.producePaymentEvent(stockToPayment);


    }

    @Override
    @Transactional
    public void rollback(StockToPayment stockToPayment) {
        var stockTransaction = stockTransactionRepository.findByOrderId(stockToPayment.getOrderId());
        var productIdList = stockTransaction.getStockTransactionProductCounts().stream().map(transaction -> transaction.getProductId()).collect(Collectors.toList());
        var stockList = stockRepository.findAllByProductIdIn(productIdList);

        stockList.forEach(stock -> {
            stockTransaction.getStockTransactionProductCounts().forEach(transaction -> {
                if (stock.getProductId().equals(transaction.getProductId())) {
                    stock.setCount(stock.getCount() + transaction.getCount());
                }
            });
        });

        stockRepository.saveAll(stockList);

        //TODO: kafka event here with status payment_failed - Mapper required.;
        OrderToStock orderToStock = new OrderToStock();
        orderToStock.setOrderId(stockToPayment.getOrderId());
        orderToStock.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        orderToStock.setUserId(stockToPayment.getUserId());
        kafkaProducer.produceStockFailedEvent(orderToStock);

    }

    @Override
    public ResponseEntity<CreateStockDTO> createStock(CreateStockDTO createStockDTO) {
        var selectedStock = stockRepository.findById(createStockDTO.getProductId());

        if (selectedStock.isPresent())
            return new ResponseEntity<>(createStockDTO, HttpStatus.CONFLICT);

        Stock stock = new Stock(createStockDTO.getProductId(), createStockDTO.getCount());
        stockRepository.save(stock);

        return new ResponseEntity<>(createStockDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CreateStockDTO> updateStock(CreateStockDTO createStockDTO) {
        var selectedStock = stockRepository.findById(createStockDTO.getProductId());

        if (selectedStock.isEmpty())
            return new ResponseEntity<>(createStockDTO, HttpStatus.NOT_FOUND);

        Stock stock = new Stock(createStockDTO.getProductId(), createStockDTO.getCount());
        stockRepository.save(stock);

        return new ResponseEntity<>(createStockDTO, HttpStatus.OK);

    }
}
