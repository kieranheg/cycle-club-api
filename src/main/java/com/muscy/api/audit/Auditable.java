package com.muscy.api.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
    
    @Column(name="CREATED")
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;
    
    @Column(name="MODIFIED")
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date modifiedDate;
}
