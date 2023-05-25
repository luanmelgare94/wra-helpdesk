package com.cdc.inlog.pe.dto.detailticket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetailTicketResponseStatisticsDto {

    private String fecha;

    private String tipoTest;

    private String porcentajeTareasCompletadas;

    private String porcentajeIncidenciasEscaladas;

    private String tiempoPromedioResolucionIncidentes;

    private String tiempoMedioEsperaRespuesta;

    private String porcentajeRetencionClientes;

}