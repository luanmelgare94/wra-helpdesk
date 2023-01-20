package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_OPERATOR;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = WORD_OPERATOR)
public class OperatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOperator;

    @Column(length = 20, nullable = false, unique = true)
    private String operator;

    @Column(nullable = false)
    private boolean active;

}