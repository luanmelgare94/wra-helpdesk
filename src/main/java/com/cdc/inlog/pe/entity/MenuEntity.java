package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_MENU;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = WORD_MENU)
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMenu;

    @Column(length = 20, nullable = false)
    private String icon;

    @Column(length = 20, nullable = false)
    private String menu;

    @Column(length = 50, nullable = false)
    private String url;

    @Column(length = 100, nullable = false)
    private String observation;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_role", joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<RoleEntity> roles;

}