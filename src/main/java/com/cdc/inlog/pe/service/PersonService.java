package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.PersonEntity;
import com.cdc.inlog.pe.service.generic.GenericService;

public interface PersonService extends GenericService<PersonEntity> {

    public boolean existsPersonEntityByIdTypeDocumentAndDoi(Integer idTypeDocument, String doi);

}