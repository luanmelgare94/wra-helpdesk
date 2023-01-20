package com.cdc.inlog.pe.dto.username;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameRequestDto {

    private UsernameRequestPersonDto persona;
    private UsernameRequestTypeContractDto tipoContratacion;
    private String nombreUsuario;
    @JsonProperty(value = "contraseña")
    private String passwd;
    private List<UsernameRequestRoleDto> roles;

    @Setter
    @Getter
    public static class UsernameRequestPersonDto {
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private UsernameRequestNationalityDto nacionalidad;
        private UsernameRequestGenderDto genero;
        private UsernameRequestTypeDocumentDto tipoDocumento;
        private String numeroDocumento;
        private LocalDate onomastico;
        private String usuarioRegistro;
        private List<UsernameRequestEmailDto> correos;
        private List<UsernameRequestPhoneDto> telefonos;
    }

    @Setter
    @Getter
    public static class UsernameRequestNationalityDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestGenderDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestTypeDocumentDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestEmailDto {
        private UsernameRequestTypeEmailDto tipoCorreo;
        private String correo;
        private String usuarioRegistro;
    }

    @Setter
    @Getter
    public static class UsernameRequestTypeEmailDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestPhoneDto {
        private UsernameRequestTypePhoneDto tipoTelefono;
        private UsernameRequestOperatorDto operadorTelefonico;
        private String telefono;
        private String usuarioRegistro;
        private String observacion;
    }

    @Setter
    @Getter
    public static class UsernameRequestTypePhoneDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestOperatorDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestRoleDto {
        private Integer codigo;
    }

    @Setter
    @Getter
    public static class UsernameRequestTypeContractDto {
        private Integer codigo;
    }

}