package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import com.cdc.inlog.pe.entity.ProvinceEntity;
import com.cdc.inlog.pe.repository.ProvinceRepository;
import com.cdc.inlog.pe.service.ProvinceService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<ProvinceEntity> getAllEntity() {
        log.info("ProvinceServiceImpl.getAllEntity");
        return null;
    }

    @Override
    public List<ProvinceEntity> getAllEntityActivated() {
        log.info("ProvinceServiceImpl.getAllEntityActivated");
        return null;
    }

    @Override
    public List<ProvinceEntity> getAllEntityDeactivated() {
        log.info("ProvinceServiceImpl.getAllEntityDeactivated");
        return null;
    }

    @Override
    public ProvinceEntity getAllEntityById(Integer id) {
        log.info("ProvinceServiceImpl.getAllEntityById");
        log.info("ProvinceServiceImpl.getAllEntityById.id: " + id);
        return null;
    }

    @Override
    public ProvinceEntity registerEntity(ProvinceEntity provinceEntity) {
        log.info("ProvinceServiceImpl.registerEntity");
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("ProvinceServiceImpl.existsEntityById");
        log.info("ProvinceServiceImpl.existsEntityById.id: " + id);
        return false;
    }

    @Override
    public Integer updateEntityById(ProvinceEntity provinceEntity) {
        log.info("ProvinceServiceImpl.updateEntityById");
        log.info("ProvinceServiceImpl.updateEntityById.id: " + provinceEntity.getIdProvince());
        return NUMBER_ZERO;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("ProvinceServiceImpl.checkActiveById");
        log.info("ProvinceServiceImpl.checkActiveById.id: " + id);
        return NUMBER_ZERO;
    }

    @Override
    public boolean activateEntityById(Integer id) {
        log.info("ProvinceServiceImpl.activateEntityById");
        log.info("ProvinceServiceImpl.activateEntityById.id: " + id);
        return false;
    }

    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("ProvinceServiceImpl.deactivateEntityById");
        log.info("ProvinceServiceImpl.deactivateEntityById.id: " + id);
        return false;
    }

}