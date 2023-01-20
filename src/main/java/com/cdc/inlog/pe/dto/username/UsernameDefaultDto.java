package com.cdc.inlog.pe.dto.username;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameDefaultDto {

    private Integer codigo;

    private UsernameDefaultPersonaDto persona;

    private String usuario;

    private String tipoContratacion;

    private List<UsernameDefaultRoleDto> roles;

    @Getter
    @Setter
    public static class UsernameDefaultPersonaDto {
        private String nombrePersona;
        private String nacionalidad;
        private String sexo;
        private String tipoDocumento;
        private String numeroDocumento;
    }

    @Getter
    @Setter
    public static class UsernameDefaultRoleDto {
        private String rol;
        private String descripcion;
    }

}