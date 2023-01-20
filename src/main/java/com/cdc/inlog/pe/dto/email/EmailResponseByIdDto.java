package com.cdc.inlog.pe.dto.email;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmailResponseByIdDto {

    private Integer codigo;

    private EmailResponseByIdCodeAndNameDto tipoCorreo;

    private String correo;

    private EmailResponseByIdCodeAndNameDto persona;

    private String usuarioRegistro;

    private LocalDate fechaRegistro;

    @Getter
    @Setter
    public static class EmailResponseByIdCodeAndNameDto {
        private Integer codigo;
        private String nombre;
    }

}