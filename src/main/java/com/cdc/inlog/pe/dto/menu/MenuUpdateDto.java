package com.cdc.inlog.pe.dto.menu;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MenuUpdateDto {

    private String icono;

    private String menu;

    private String link;

    private String observacion;

    private List<MenuUpdateRolDto> roles;

    @Getter
    @Setter
    public static class MenuUpdateRolDto {
        private Integer codigo;
    }

}