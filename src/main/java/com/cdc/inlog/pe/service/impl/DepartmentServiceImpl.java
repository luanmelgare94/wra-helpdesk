package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import com.cdc.inlog.pe.entity.DepartmentEntity;
import com.cdc.inlog.pe.repository.DepartmentRepository;
import com.cdc.inlog.pe.service.DepartmentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentEntity> getAllEntity() {
        log.info("DepartmentServiceImpl.getAllEntity");
        return null;
    }

    @Override
    public List<DepartmentEntity> getAllEntityActivated() {
        log.info("DepartmentServiceImpl.getAllEntityActivated");
        return null;
    }

    @Override
    public List<DepartmentEntity> getAllEntityDeactivated() {
        log.info("DepartmentServiceImpl.getAllEntityDeactivated");
        return null;
    }

    @Override
    public DepartmentEntity getAllEntityById(Integer id) {
        log.info("DepartmentServiceImpl.getAllEntityById");
        log.info("DepartmentServiceImpl.getAllEntityById.id: " + id);
        return null;
    }

    @Override
    public DepartmentEntity registerEntity(DepartmentEntity departmentEntity) {
        log.info("DepartmentServiceImpl.registerEntity");
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("DepartmentServiceImpl.existsEntityById");
        log.info("DepartmentServiceImpl.existsEntityById.id: " + id);
        return false;
    }

    @Override
    public Integer updateEntityById(DepartmentEntity departmentEntity) {
        log.info("DepartmentServiceImpl.updateEntityById");
        log.info("DepartmentServiceImpl.updateEntityById.id: " + departmentEntity.getCodeDepartment());
        return NUMBER_ZERO;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("DepartmentServiceImpl.checkActiveById");
        log.info("DepartmentServiceImpl.checkActiveById.id: " + id);
        return NUMBER_ZERO;
    }

    @Override
    public boolean activateEntityById(Integer id) {
        log.info("DepartmentServiceImpl.activateEntityById");
        log.info("DepartmentServiceImpl.activateEntityById.id: " + id);
        return false;
    }

    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("DepartmentServiceImpl.deactivateEntityById");
        log.info("DepartmentServiceImpl.deactivateEntityById.id: " + id);
        return false;
    }

}