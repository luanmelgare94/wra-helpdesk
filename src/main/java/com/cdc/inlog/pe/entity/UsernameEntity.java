package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_USERNAME;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = WORD_USERNAME)
public class UsernameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsername;

    @OneToOne(targetEntity = PersonEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person", nullable = false, foreignKey = @ForeignKey(name = "FK_username_person"))
    private PersonEntity personEntity;

    @ManyToOne
    @JoinColumn(name = "id_type_contract", foreignKey = @ForeignKey(name = "FK_username_type_contract"))
    private TypeContractEntity typeContractEntity;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String passwd;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "username_role",
            joinColumns = @JoinColumn(name = "id_username", referencedColumnName = "idUsername"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<RoleEntity> roles;

}