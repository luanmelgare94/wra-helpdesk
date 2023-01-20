package com.cdc.inlog.pe.dto.detailticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailTicketRequestDto {

    private Integer codigoTicket;

    private Integer codigoEstadoTicket;

    private Integer usuario;

    private String descripcion;

    private String observacion;

}