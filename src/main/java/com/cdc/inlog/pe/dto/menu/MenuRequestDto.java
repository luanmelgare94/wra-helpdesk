package com.cdc.inlog.pe.dto.menu;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MenuRequestDto {

    private String icono;

    private String menu;

    private String link;

    private String observacion;

    private List<MenuRequestRoleDto> roles;

    @Getter
    @Setter
    public static class MenuRequestRoleDto {
        private Integer codigo;
    }

}