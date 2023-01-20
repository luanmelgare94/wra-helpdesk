package com.cdc.inlog.pe.dto.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponseDto {

    private EmailResponseNameDto tipoCorreo;

    private EmailResponseNameDto persona;

    @Getter
    @Setter
    public static class EmailResponseNameDto {
        private String nombre;
    }

}