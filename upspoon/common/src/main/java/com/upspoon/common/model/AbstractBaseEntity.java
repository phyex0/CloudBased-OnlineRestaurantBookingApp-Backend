package com.upspoon.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author burak.yesildal
 */


@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected UUID id;
}
