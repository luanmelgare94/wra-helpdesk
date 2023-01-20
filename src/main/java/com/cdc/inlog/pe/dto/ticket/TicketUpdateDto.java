package com.cdc.inlog.pe.dto.ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdateDto {

    private Integer codigoTipoTicket;

    private String descripcion;

    private String observacion;

}