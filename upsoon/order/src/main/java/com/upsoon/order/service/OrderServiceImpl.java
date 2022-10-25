package com.upsoon.order.service;

import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.order.mapper.OrganizationFromOrganizationServiceMapper;
import com.upsoon.order.producer.KafkaProducer;
import com.upsoon.order.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Halit Burak Yeşildal
 */


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper;
    private final OrganizationRepository organizationRepository;
    private final KafkaProducer kafkaProducer;


    public OrderServiceImpl(OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper, OrganizationRepository organizationRepository, KafkaProducer kafkaProducer) {
        this.organizationFromOrganizationServiceMapper = organizationFromOrganizationServiceMapper;
        this.organizationRepository = organizationRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationToOrder organizationToOrder) {
        var organization = organizationFromOrganizationServiceMapper.toEntity(organizationToOrder);
        organizationRepository.save(organization);
    }
}
