package com.cdc.inlog.pe.dto.phone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneResponseDto {

    private PhoneResponseNameDto tipoTelefono;

    private PhoneResponseNameDto operadorTelefonico;

    private PhoneResponseNameDto persona;

    @Getter
    @Setter
    public static class PhoneResponseNameDto {
        private String nombre;
    }

}