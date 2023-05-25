package com.cdc.inlog.pe.dto.detailticket;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailTicketAuxiliarRepository {

    private LocalDate fecha;

    private Integer ticket;

}