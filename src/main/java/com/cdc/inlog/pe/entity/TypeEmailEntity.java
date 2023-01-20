package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_EMAIL;
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
@Table(name = WORD_TYPE_EMAIL)
public class TypeEmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeEmail;

    @Column(length = 25, nullable = false, unique = true)
    private String typeEmail;

    @Column(nullable = false)
    private boolean active;

}