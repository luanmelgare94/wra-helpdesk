package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_TICKET;
import java.time.LocalDate;
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
@Table(name = WORD_TYPE_TICKET)
@Entity
public class TypeTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeTicket;

    @Column(length = 30, nullable = false, unique = true)
    private String typeTicket;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;

}