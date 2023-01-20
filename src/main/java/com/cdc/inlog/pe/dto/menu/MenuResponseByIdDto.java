package com.cdc.inlog.pe.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponseByIdDto {

    private Integer codigo;

    private String icono;

    private String menu;

    private String link;

    private String observacion;

    private List<MenuResponseByIdRol> roles;

    @Getter
    @Setter
    public static class MenuResponseByIdRol {
        private Integer codigo;
        private String rol;
        private String descripcion;
    }

}