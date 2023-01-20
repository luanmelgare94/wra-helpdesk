package com.cdc.inlog.pe.dto.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailUpdateDto {

    private EmailUpdateCodeDto tipoCorreo;

    private String correo;

    @Getter
    @Setter
    public static class EmailUpdateCodeDto {
        private Integer codigo;
    }

}