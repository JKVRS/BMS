package com.development.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass // Don't create a separate table, instead put its attributes to every child
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate // it will generate the creattion date
    @Temporal(TemporalType.TIME)
    private Date creationDate;


}
