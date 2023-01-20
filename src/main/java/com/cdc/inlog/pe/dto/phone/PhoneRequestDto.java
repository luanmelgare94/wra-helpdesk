package com.cdc.inlog.pe.dto.phone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequestDto {

    private PhoneRequestCodeDto tipoTelefono;

    private PhoneRequestCodeDto persona;

    private PhoneRequestCodeDto operadorTelefonico;

    private String telefono;

    private String usuarioRegistro;

    private String observacion;

    @Getter
    @Setter
    public static class PhoneRequestCodeDto {
        private Integer codigo;
    }

}