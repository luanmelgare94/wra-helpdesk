package com.cdc.inlog.pe.dto.ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDto {

    private TicketRequestCodeDto usuarioRegistro;

    private String asunto;

    private String descripcion;

    private String observacion;

    @Getter
    @Setter
    public static class TicketRequestCodeDto {
        private Integer codigo;
    }

}