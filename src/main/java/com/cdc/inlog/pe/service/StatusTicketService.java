package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.StatusTicketEntity;
import com.cdc.inlog.pe.service.generic.GenericService;

import java.util.List;

public interface StatusTicketService extends GenericService<StatusTicketEntity> {

    public List<StatusTicketEntity> getAllStatusTicketActivatedByIdTicket(Integer idTicket);

}