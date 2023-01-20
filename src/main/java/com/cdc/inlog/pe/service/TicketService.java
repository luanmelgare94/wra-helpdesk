package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.TicketEntity;
import com.cdc.inlog.pe.service.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService extends GenericService<TicketEntity> {

    public Page<TicketEntity> getAllEntity(Pageable pageable);

    public Page<TicketEntity> getAllEntityActivated(Pageable pageable);

    public Page<TicketEntity> getAllEntityDeactivated(Pageable pageable);

}