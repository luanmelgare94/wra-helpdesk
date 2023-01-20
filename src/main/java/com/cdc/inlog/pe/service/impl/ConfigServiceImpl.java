package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;
import com.cdc.inlog.pe.entity.ConfigEntity;
import com.cdc.inlog.pe.repository.ConfigRepository;
import com.cdc.inlog.pe.service.ConfigService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public List<ConfigEntity> getAllEntity() {
        log.info("ConfigServiceImpl.getAllEntity");
        return new ArrayList<>();
    }

    @Override
    public List<ConfigEntity> getAllEntityActivated() {
        log.info("ConfigServiceImpl.getAllEntityActivated");
        return new ArrayList<>();
    }

    @Override
    public List<ConfigEntity> getAllEntityDeactivated() {
        log.info("ConfigServiceImpl.getAllEntityDeactivated");
        return new ArrayList<>();
    }

    @Override
    public ConfigEntity getAllEntityById(Integer id) {
        log.info("ConfigServiceImpl.getAllEntityById");
        log.info("ConfigServiceImpl.getAllEntityById.idConfig: " + id);
        return configRepository.findById(id).orElse(new ConfigEntity());
    }

    @Override
    public ConfigEntity registerEntity(ConfigEntity configEntity) {
        log.info("ConfigServiceImpl.registerEntity");
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("ConfigServiceImpl.existsEntityById");
        log.info("ConfigServiceImpl.existsEntityById.idConfig: " + id);
        return configRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(ConfigEntity configEntity) {
        log.info("ConfigServiceImpl.updateEntityById");
        log.info("ConfigServiceImpl.updateEntityById.idConfig: " + configEntity.getIdConfig());
        if (configRepository.existsById(configEntity.getIdConfig())) {
            return configRepository.updateConfigEntityByIdConfig(
                    configEntity.getParameter(),
                    configEntity.getStatus(),
                    LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU)),
                    configEntity.getIdConfig()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("ConfigServiceImpl.checkActiveById");
        log.info("ConfigServiceImpl.checkActiveById.idConfig: " + id);
        return null;
    }

    @Override
    public boolean activateEntityById(Integer id) {
        log.info("ConfigServiceImpl.activateEntityById");
        log.info("ConfigServiceImpl.activateEntityById.idConfig: " + id);
        return false;
    }

    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("ConfigServiceImpl.deactivateEntityById");
        log.info("ConfigServiceImpl.deactivateEntityById.idConfig: " + id);
        return false;
    }

}