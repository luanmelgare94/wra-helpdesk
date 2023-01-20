package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_TICKET;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = WORD_TICKET)
@Entity
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTicket;

    @ManyToOne
    @JoinColumn(name = "id_type_ticket", nullable = false, foreignKey = @ForeignKey(name = "FK_ticket_type_ticket"))
    private TypeTicketEntity typeTicketEntity;

    @ManyToOne
    @JoinColumn(name = "id_username", nullable = false, foreignKey = @ForeignKey(name = "FK_ticket_username"))
    private UsernameEntity usernameEntity;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String observation;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

    @OneToMany(mappedBy = "ticketEntity", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<DetailTicketEntity> detailTicketEntityList;

}