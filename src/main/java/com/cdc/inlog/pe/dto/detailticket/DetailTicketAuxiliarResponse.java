package com.cdc.inlog.pe.dto.detailticket;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DetailTicketAuxiliarResponse {

    private LocalDate fecha;

    private String tiempoEsperaRespuesta;

}