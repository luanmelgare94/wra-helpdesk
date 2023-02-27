package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.EmailEntity;
import com.cdc.inlog.pe.service.generic.GenericService;

import java.util.List;

public interface EmailService extends GenericService<EmailEntity> {

    public List<EmailEntity> getAllEmailEntityActivatedByIdPerson(Integer idPerson);

    public List<EmailEntity> getAllEmailEntityDeactivatedByIdPerson(Integer idPerson);

}