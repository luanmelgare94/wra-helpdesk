package com.cdc.inlog.pe.dto.username;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameResponseDto {

    private String nombrePersona;

    private String tipoContratacion;

    private String usuario;

    private List<UsernameResponseRolesDto> roles;

    @Getter
    @Setter
    public static class UsernameResponseRolesDto {
        private String rol;
        private String descripcion;
    }

}