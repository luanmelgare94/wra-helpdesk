package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_ROLE;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = WORD_ROLE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Column(length = 40, nullable = false)
    private String role;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

}