package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_DETAIL_TICKET;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = WORD_DETAIL_TICKET)
@Entity
public class DetailTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetailTicket;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_ticket", nullable = false, foreignKey = @ForeignKey(name = "FK_detail_ticket_ticket"))
    private TicketEntity ticketEntity;

    @ManyToOne
    @JoinColumn(name = "id_status_ticket", nullable = false, foreignKey = @ForeignKey(name = "FK_detail_ticket_status_ticket"))
    private StatusTicketEntity statusTicketEntity;

    @ManyToOne
    @JoinColumn(name = "id_username", nullable = false, foreignKey = @ForeignKey(name = "FK_detail_ticket_username"))
    private UsernameEntity usernameEntity;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String observation;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

}