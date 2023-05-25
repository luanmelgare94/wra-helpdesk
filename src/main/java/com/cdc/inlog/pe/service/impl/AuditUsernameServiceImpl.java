package com.cdc.inlog.pe.service.impl;

import com.cdc.inlog.pe.entity.AuditUsernameEntity;
import com.cdc.inlog.pe.repository.AuditUsernameRepository;
import com.cdc.inlog.pe.service.AuditUsernameService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuditUsernameServiceImpl implements AuditUsernameService {

    @Autowired
    private AuditUsernameRepository auditUsernameRepository;

    @Override
    public List<AuditUsernameEntity> getAllEntity() {
        log.info("AuditUsernameServiceImpl.getAllEntity");
        return auditUsernameRepository.findAll();
    }

    @Override
    public List<AuditUsernameEntity> getAllEntityActivated() {
        return null;
    }

    @Override
    public List<AuditUsernameEntity> getAllEntityDeactivated() {
        return null;
    }

    @Override
    public AuditUsernameEntity getAllEntityById(Integer id) {
        return null;
    }

    @Override
    public AuditUsernameEntity registerEntity(AuditUsernameEntity auditUsernameEntity) {
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        return false;
    }

    @Override
    public Integer updateEntityById(AuditUsernameEntity auditUsernameEntity) {
        return null;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        return null;
    }

    @Override
    public boolean activateEntityById(Integer id) {
        return false;
    }

    @Override
    public boolean deactivateEntityById(Integer id) {
        return false;
    }
}