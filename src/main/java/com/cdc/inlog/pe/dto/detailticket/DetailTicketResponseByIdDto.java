package com.cdc.inlog.pe.dto.detailticket;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailTicketResponseByIdDto {

    private Integer codigo;

    private DetailTicketTicketResponseByIdDto datosTicket;

    private DetailTicketStatusTicketResponseByIdDto datosEstadoTicket;

    private DetailTicketUsernameResponseByIdDto datosUsuario;

    private String descripcion;

    private String observacion;

    private LocalDateTime fechoraRegistro;

    @Getter
    @Setter
    public static class DetailTicketTicketResponseByIdDto {
        private String tipoTicket;
        private DetailTicketUsernameResponseByIdDto datosUsuario;
        private LocalDateTime fechoraRegistro;
    }

    @Getter
    @Setter
    public static class DetailTicketUsernameResponseByIdDto {
        private String usuario;
        private String nombrePersona;
    }

    @Getter
    @Setter
    public static class DetailTicketStatusTicketResponseByIdDto {
        private String estadoTicket;
        private String codigoColor;
    }

}