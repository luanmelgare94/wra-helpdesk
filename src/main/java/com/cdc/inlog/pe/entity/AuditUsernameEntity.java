package com.cdc.inlog.pe.entity;

import java.time.LocalDate;
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
@Table(name = "auditUsername")
public class AuditUsernameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuditUsername;

    private LocalDate dateRegister;

    private Integer quantityUsersEndOfPeriod;

    private Integer quantityNewUsers;

    private Integer quantityUsersBeginningPeriod;

}