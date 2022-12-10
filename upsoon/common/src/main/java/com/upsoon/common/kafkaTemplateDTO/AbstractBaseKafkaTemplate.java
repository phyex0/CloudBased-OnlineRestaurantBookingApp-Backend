package com.upsoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseKafkaTemplate {

    //TODO: remove class!

    public String id = UUID.randomUUID().toString();

    public LocalDate messageDate = LocalDate.now();

}
