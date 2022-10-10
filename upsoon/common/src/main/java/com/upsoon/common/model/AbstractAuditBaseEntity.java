package com.upsoon.common.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.Instant;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class AbstractAuditBaseEntity extends AbstractBaseEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    //@JsonIgnore
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    //@JsonIgnore
    protected Instant createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false, updatable = false)
    //@JsonIgnore
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false, updatable = false)
    //@JsonIgnore
    protected Instant lastUpdatedDate;

    protected Boolean deleted = Boolean.FALSE;

    @PrePersist
    public void prePersists() {
        this.deleted = Boolean.FALSE;
    }


}
