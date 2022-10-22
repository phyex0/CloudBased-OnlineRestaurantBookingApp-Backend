package com.upsoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseKafkaTemplate {

    public String id = UUID.randomUUID().toString();

    public LocalDate messageDate = LocalDate.now();

}
