package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_DOCUMENT;
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
@Table(name = WORD_TYPE_DOCUMENT)
public class TypeDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeDocument;

    @Column(length = 12, nullable = false, unique = true)
    private String abbreviation;

    @Column(length = 40, nullable = false, unique = true)
    private String typeDocument;

    @Column(nullable = false)
    private boolean active;

}