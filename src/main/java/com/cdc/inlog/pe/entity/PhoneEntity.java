package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_PHONE;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = WORD_PHONE)
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPhone;

    @ManyToOne
    @JoinColumn(name = "id_type_phone", nullable = false, foreignKey = @ForeignKey(name = "FK_phone_type_phone"))
    private TypePhoneEntity typePhoneEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false, foreignKey = @ForeignKey(name = "FK_phone_person"))
    private PersonEntity personEntity;

    @ManyToOne
    @JoinColumn(name = "id_operator", nullable = false, foreignKey = @ForeignKey(name = "FK_phone_operator"))
    private OperatorEntity operatorEntity;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String userRegistration;

    @Column(length = 100)
    private String observation;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean userActive;

    @Column(nullable = false)
    private LocalDate dateRegister;

}