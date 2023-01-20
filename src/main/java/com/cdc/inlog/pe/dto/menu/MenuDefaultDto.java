package com.cdc.inlog.pe.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuDefaultDto {

    private Integer codigo;

    private String icono;

    private String menu;

    private String link;

    private String observacion;

    private List<MenuDefaultRolesDto> roles;

    @Getter
    @Setter
    public static class MenuDefaultRolesDto {
        private String rol;
        private String descripcion;
    }

}