package com.khsa.javaee.model;

import com.khsa.javaee.utils.MockUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @Getter
    @Setter
    @Builder.Default
    private String id = MockUtils.randomId();

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date updated;
}
