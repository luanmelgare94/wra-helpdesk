package com.cdc.inlog.pe.dto.ticket;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDefaultDto {

    private Integer codigo;

    private String categoriaTicket;

    private TicketDefaultPrioridadDto prioridad;

    private TicketDefaultUsernameDto datosUsuario;

    private String asunto;

    private String descripcion;

    private TicketDefaultDetailTicketDto datosAtencion;

    private TicketDefaultUsernameDto asesorAsignado;

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

    @Getter
    @Setter
    public static class TicketDefaultPrioridadDto {
        private String prioridad;
        private String codigoColor;
    }

}