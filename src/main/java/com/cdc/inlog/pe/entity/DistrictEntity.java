package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_DISTRICT;
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
@Table(name = WORD_DISTRICT)
public class DistrictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDistrict;

    @ManyToOne
    @JoinColumn(name = "id_province", nullable = false, foreignKey = @ForeignKey(name = "FK_district_province"))
    private ProvinceEntity provinceEntity;

    @Column(length = 3, nullable = false)
    private String codeDistrict;

    @Column(length = 40, nullable = false)
    private String district;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;


}