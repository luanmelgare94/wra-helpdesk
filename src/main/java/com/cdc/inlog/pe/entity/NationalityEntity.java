package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_NATIONALITY;
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
@Table(name = WORD_NATIONALITY)
public class NationalityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNationality;

    @Column(length = 2, nullable = false, unique = true)
    private String iso;

    @Column(length = 80, nullable = false, unique = true)
    private String country;

    @Column(nullable = false)
    private boolean active;

}