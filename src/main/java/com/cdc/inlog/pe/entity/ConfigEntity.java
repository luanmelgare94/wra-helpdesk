package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_CONFIG;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = WORD_CONFIG)
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfig;

    @Column(length = 100, nullable = false)
    private String parameter;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

    private LocalDateTime dateUpdate;

}