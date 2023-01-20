package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_STATUS_TICKET;
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
@Table(name = WORD_STATUS_TICKET)
@Entity
public class StatusTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatusTicket;

    @Column(length = 30, nullable = false, unique = true)
    private String statusTicket;

    @Column(length = 10, nullable = false)
    private String codeColor;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;

}