package com.cdc.inlog.pe.dto.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDto {

    private EmailRequestCodeDto tipoCorreo;

    private EmailRequestCodeDto persona;

    private String correo;

    private String usuarioRegistro;

    @Getter
    @Setter
    public static class EmailRequestCodeDto {
        private Integer codigo;
    }

}