package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_GENDER;
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
@Table(name = WORD_GENDER)
public class GenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGender;

    @Column(length = 1, nullable = false, unique = true)
    private String iso;

    @Column(length = 10, nullable = false, unique = true)
    private String gender;

    @Column(nullable = false)
    private boolean active;

}