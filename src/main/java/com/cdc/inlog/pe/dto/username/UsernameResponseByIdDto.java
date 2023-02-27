package com.cdc.inlog.pe.dto.username;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameResponseByIdDto {

    private Integer codigo;

    private UsernameResponseByIdPersonDto persona;

    private UsernameResponseByIdTypeContractDto tipoContratacion;

    private String nombreUsuario;

    private List<UsernameResponseByIdRoleDto> roles;

    @Getter
    @Setter
    public static class UsernameResponseByIdPersonDto {
        private Integer codigo;
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private UsernameResponseByIdNationalityDto nacionalidad;
        private UsernameResponseByIdGenderDto genero;
        private UsernameResponseByIdTypeDocumentDto tipoDocumento;
        private String numeroDocumento;
        private LocalDate onomastico;
        private String usuarioRegistro;
        private List<UsernameResponseByIdEmailDto> correos;
        private List<UsernameResponseByIdPhoneDto> telefonos;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdNationalityDto {
        private Integer codigo;
        private String nacionalidad;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdGenderDto {
        private Integer codigo;
        private String genero;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdTypeDocumentDto {
        private Integer codigo;
        private String tipoDocumento;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdEmailDto {
        private Integer codigo;
        private UsernameResponseByIdTypeEmailDto tipoCorreo;
        private String correo;
        private String usuarioRegistro;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdTypeEmailDto {
        private Integer codigo;
        private String tipoCorreo;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdPhoneDto {
        private Integer codigo;
        private UsernameResponseByIdTypePhoneDto tipoTelefono;
        private UsernameResponseByIdOperatorDto operadorTelefonico;
        private String telefono;
        private String usuarioRegistro;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdTypePhoneDto {
        private Integer codigo;
        private String tipoTelefono;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdOperatorDto {
        private Integer codigo;
        private String operador;
    }

    @Setter
    @Getter
    public static class UsernameResponseByIdRoleDto {
        private Integer codigo;
        private String rol;
        private String descripcion;
    }

    @Getter
    @Setter
    public static class UsernameResponseByIdTypeContractDto {
        private Integer codigo;
        private String nombre;
    }

}