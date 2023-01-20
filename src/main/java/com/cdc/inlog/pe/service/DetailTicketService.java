package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.DetailTicketEntity;
import com.cdc.inlog.pe.service.generic.GenericService;

import java.util.List;

public interface DetailTicketService extends GenericService<DetailTicketEntity> {

    public List<DetailTicketEntity> getAllDetailTicketEntityActivateByIdStatusTicket(Integer idStatusTicket);

}