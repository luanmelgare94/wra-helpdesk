package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_PRIORITY;
import java.time.LocalDate;
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
@Table(name = WORD_PRIORITY)
public class PriorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPriority;

    @Column(length = 30, nullable = false, unique = true)
    private String priority;

    @Column(length = 10, nullable = false)
    private String codeColor;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;

}