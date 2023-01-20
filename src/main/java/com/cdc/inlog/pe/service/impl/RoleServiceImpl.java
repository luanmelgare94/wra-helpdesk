package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_ROLE;
import com.cdc.inlog.pe.entity.RoleEntity;
import com.cdc.inlog.pe.repository.RoleRepository;
import com.cdc.inlog.pe.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getAllEntity() {
        log.info("RoleServiceImpl.getAllEntity");
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ROLE));
    }

    @Override
    public List<RoleEntity> getAllEntityActivated() {
        log.info("RoleServiceImpl.getAllEntityActivated");
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ROLE))
                .stream()
                .filter(RoleEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleEntity> getAllEntityDeactivated() {
        log.info("RoleServiceImpl.getAllEntityDeactivated");
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ROLE))
                .stream()
                .filter(roleEntity -> !roleEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public RoleEntity getAllEntityById(Integer id) {
        log.info("RoleServiceImpl.getAllEntityById");
        log.info("RoleServiceImpl.getAllEntityById.id: " + id);
        return roleRepository.findById(id)
                .orElse(new RoleEntity());
    }

    @Override
    public RoleEntity registerEntity(RoleEntity roleEntity) {
        log.info("RoleServiceImpl.registerEntity");
        return roleRepository.save(roleEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("RoleServiceImpl.existsEntityById");
        log.info("RoleServiceImpl.existsEntityById.id: " + id);
        return roleRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(RoleEntity roleEntity) {
        log.info("RoleServiceImpl.updateEntityById");
        log.info("RoleServiceImpl.updateEntityById.id: " + roleEntity.getIdRole());
        if (roleRepository.existsById(roleEntity.getIdRole())) {
            return roleRepository.updateRoleEntityRoleAndDescriptionByIdRole(
                    roleEntity.getRole(),
                    roleEntity.getDescription(),
                    roleEntity.getIdRole()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("RoleServiceImpl.checkActiveById");
        log.info("RoleServiceImpl.checkActiveById.id: " + id);
        if (!roleRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return roleRepository.getActiveOfRoleEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("RoleServiceImpl.activateEntityById");
        log.info("RoleServiceImpl.activateEntityById.id: " + id);
        return roleRepository.updateActiveOfRoleEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("RoleServiceImpl.deactivateEntityById");
        log.info("RoleServiceImpl.deactivateEntityById.id: " + id);
        return roleRepository.updateActiveOfRoleEntityById(false, id) == NUMBER_ONE;
    }

}