package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_EMAIL;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Setter
@Getter
@Entity
@Table(name = WORD_EMAIL)
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmail;

    @ManyToOne
    @JoinColumn(name = "id_type_email", nullable = false, foreignKey = @ForeignKey(name = "FK_email_type_email"))
    private TypeEmailEntity typeEmailEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false, foreignKey = @ForeignKey(name = "FK_email_person"))
    private PersonEntity personEntity;

    @Email
    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String userRegistration;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean userActive;

    @Column(nullable = false)
    private LocalDate dateRegister;

}