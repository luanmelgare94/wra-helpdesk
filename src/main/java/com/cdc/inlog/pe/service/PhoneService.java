package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.PhoneEntity;
import com.cdc.inlog.pe.service.generic.GenericService;

import java.util.List;

public interface PhoneService extends GenericService<PhoneEntity> {

    public List<PhoneEntity> getAllPhoneEntityActivatedByIdPerson(Integer idPerson);

    public List<PhoneEntity> getAllPhoneEntityDeactivatedByIdPerson(Integer idPerson);

}