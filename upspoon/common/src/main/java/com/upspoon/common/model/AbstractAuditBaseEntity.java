package com.upspoon.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;

/**
 * @author burak.yesildal
 */

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public abstract class AbstractAuditBaseEntity extends AbstractBaseEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    @JsonIgnore
    protected String createdBy = "postman";

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    @JsonIgnore
    protected Instant createdDate = new Date().toInstant();

    //    @LastModifiedBy
//    @Column(name = "last_modified_by", nullable = false, updatable = false)
//    @JsonIgnore
//    protected String lastModifiedBy;
//
//    @LastModifiedDate
//    @Column(name = "last_modified_date", nullable = false, updatable = false)
//    @JsonIgnore
//    protected Instant lastUpdatedDate;
    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    protected Boolean deleted = Boolean.FALSE;

    @PrePersist
    public void prePersists() {
        this.deleted = Boolean.FALSE;
    }


}
