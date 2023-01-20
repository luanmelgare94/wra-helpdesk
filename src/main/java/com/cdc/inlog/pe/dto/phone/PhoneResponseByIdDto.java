package com.cdc.inlog.pe.dto.phone;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PhoneResponseByIdDto {

    private Integer codigo;

    private PhoneResponseByIdCodeAndNameDto tipoTelefono;

    private PhoneResponseByIdCodeAndNameDto persona;

    private PhoneResponseByIdCodeAndNameDto operadorTelefonico;

    private String telefono;

    private String usuarioRegistro;

    private String observacion;

    private LocalDate fechaRegistro;

    @Getter
    @Setter
    public static class PhoneResponseByIdCodeAndNameDto {
        private Integer codigo;
        private String nombre;
    }

}