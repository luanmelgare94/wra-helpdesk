package com.cdc.inlog.pe.dto.phone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneUpdateDto {

    private PhoneUpdateCodeDto tipoTelefono;

    private PhoneUpdateCodeDto operadorTelefonico;

    private String telefono;

    @Getter
    @Setter
    public static class PhoneUpdateCodeDto {
        private Integer codigo;
    }

}