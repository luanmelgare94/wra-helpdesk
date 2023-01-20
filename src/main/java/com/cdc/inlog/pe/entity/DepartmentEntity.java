package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_DEPARTMENT;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = WORD_DEPARTMENT)
public class DepartmentEntity {

    @Id
    @Column(length = 3)
    private String codeDepartment;

    @Column(length = 20, nullable = false, unique = true)
    private String department;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDate dateRegister;

}