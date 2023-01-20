package com.cdc.inlog.pe.dto.ticket;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseByIdDto {

    private Integer codigo;

    private TicketResponseByIdCodeAndNameDto datosTipoTicket;

    private TicketResponseByIdUsernameDto datosUsuario;

    private String descripcion;

    private String observacion;

    private LocalDateTime fechaRegistro;

    private List<TicketResponseByIdDetailTicketDto> detallesTicket;

    @Getter
    @Setter
    public static class TicketResponseByIdCodeAndNameDto {
        private Integer codigo;
        private String nombre;
    }

    @Getter
    @Setter
    public static class TicketResponseByIdDetailTicketDto {
        private Integer codigo;
        private TicketResponseByIdStatusTicketDto datosEstadoTicket;
        private TicketResponseByIdUsernameDto datosUsuario;
        private String descripcion;
        private String observacion;
        private LocalDateTime fechaRegistro;
    }

    @Getter
    @Setter
    public static class TicketResponseByIdStatusTicketDto {
        private Integer codigo;
        private String codigoColor;
        private String nombre;
    }

    @Getter
    @Setter
    public static class TicketResponseByIdUsernameDto {
        private Integer codigo;
        private String usuario;
        private String nombrePersona;
    }

}