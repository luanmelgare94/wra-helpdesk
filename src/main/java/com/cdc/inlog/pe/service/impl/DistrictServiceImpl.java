package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import com.cdc.inlog.pe.entity.DistrictEntity;
import com.cdc.inlog.pe.service.DistrictService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistrictServiceImpl implements DistrictService {

    @Override
    public List<DistrictEntity> getAllEntity() {
        log.info("DistrictServiceImpl.getAllEntity");
        return null;
    }

    @Override
    public List<DistrictEntity> getAllEntityActivated() {
        log.info("DistrictServiceImpl.getAllEntityActivated");
        return null;
    }

    @Override
    public List<DistrictEntity> getAllEntityDeactivated() {
        log.info("DistrictServiceImpl.getAllEntityDeactivated");
        return null;
    }

    @Override
    public DistrictEntity getAllEntityById(Integer id) {
        log.info("DistrictServiceImpl.getAllEntityById");
        log.info("DistrictServiceImpl.getAllEntityById.id: " + id);
        return null;
    }

    @Override
    public DistrictEntity registerEntity(DistrictEntity districtEntity) {
        log.info("DistrictServiceImpl.registerEntity");
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("DistrictServiceImpl.existsEntityById");
        log.info("DistrictServiceImpl.existsEntityById.id: " + id);
        return false;
    }

    @Override
    public Integer updateEntityById(DistrictEntity districtEntity) {
        log.info("DistrictServiceImpl.updateEntityById");
        log.info("DistrictServiceImpl.updateEntityById.id: " + districtEntity.getIdDistrict());
        return NUMBER_ZERO;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("DistrictServiceImpl.checkActiveById");
        log.info("DistrictServiceImpl.checkActiveById.id: " + id);
        return NUMBER_ZERO;
    }

    @Override
    public boolean activateEntityById(Integer id) {
        log.info("DistrictServiceImpl.activateEntityById");
        log.info("DistrictServiceImpl.activateEntityById.id: " + id);
        return false;
    }

    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("DistrictServiceImpl.deactivateEntityById");
        log.info("DistrictServiceImpl.deactivateEntityById.id: " + id);
        return false;
    }

}