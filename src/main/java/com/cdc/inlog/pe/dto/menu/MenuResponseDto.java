package com.cdc.inlog.pe.dto.menu;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MenuResponseDto {

    private String icono;

    private String menu;

    private String link;

    private List<MenuResponseRoleDto> roles;

    @Getter
    @Setter
    public static class MenuResponseRoleDto {
        private String rol;
        private String descripcion;
    }

}