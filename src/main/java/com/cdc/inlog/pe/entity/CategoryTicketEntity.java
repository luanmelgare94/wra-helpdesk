package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_CATEGORY_TICKET;
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
@Table(name = WORD_CATEGORY_TICKET)
public class CategoryTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoryTicket;

    @Column(length = 30, nullable = false, unique = true)
    private String categoryTicket;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;

}