package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_PROVINCE;
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
@Table(name = WORD_PROVINCE)
public class ProvinceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvince;

    @ManyToOne
    @JoinColumn(name = "code_department", nullable = false, foreignKey = @ForeignKey(name = "FK_province_department"))
    private DepartmentEntity departmentEntity;

    @Column(length = 3, nullable = false)
    private String codeProvince;

    @Column(length = 40, nullable = false)
    private String province;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;

}