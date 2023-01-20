package com.cdc.inlog.pe.dto.person;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PersonResponseByIdDto {

    private Integer codigo;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private PersonResponseByIdCodeAndNameDto nacionalidad;

    private PersonResponseByIdCodeAndNameDto genero;

    private PersonResponseByIdCodeAndNameDto tipoDocumento;

    private String numeroDocumento;

    private LocalDate onomastico;

    private String usuarioRegistro;

    private List<PersonResponseByIdEmailDto> correos;

    private List<PersonResponseByIdPhoneDto> telefonos;

    @Getter
    @Setter
    public static class PersonResponseByIdCodeAndNameDto {
        private Integer codigo;
        private String nombre;
    }

    @Getter
    @Setter
    public static class PersonResponseByIdEmailDto {
        private Integer codigo;
        private PersonResponseByIdCodeAndNameDto tipoCorreo;
        private String correo;
        private String usuarioRegistro;
        private LocalDate fechaRegistro;
    }

    @Getter
    @Setter
    public static class PersonResponseByIdPhoneDto {
        private Integer codigo;
        private PersonResponseByIdCodeAndNameDto tipoTelefono;
        private PersonResponseByIdCodeAndNameDto operadorTelefonico;
        private String telefono;
        private String usuarioRegistro;
        private String observacion;
        private LocalDate fechaRegistro;
    }

}