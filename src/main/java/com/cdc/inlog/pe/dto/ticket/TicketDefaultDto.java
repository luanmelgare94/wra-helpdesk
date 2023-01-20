package com.cdc.inlog.pe.dto.ticket;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDefaultDto {

    private Integer codigo;

    private String tipoTicket;

    private TicketDefaultUsernameDto datosUsuario;

    private String descripcion;

    private TicketDefaultDetailTicketDto datosAtencion;

    private LocalDateTime fechoraCreacion;

    @Getter
    @Setter
    public static class TicketDefaultUsernameDto {
        private String usuario;
        private String nombrePersona;
    }

    @Getter
    @Setter
    public static class TicketDefaultDetailTicketDto {
        private String estado;
        private String codigoColor;
    }

}