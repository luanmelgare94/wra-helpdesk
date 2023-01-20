package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_CONTRACT;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = WORD_TYPE_CONTRACT)
public class TypeContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeContract;

    @Column(length = 60, nullable = false, unique = true)
    private String typeContract;

    @Column(nullable = false)
    private boolean active;

}