package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_PHONE;
import com.cdc.inlog.pe.entity.TypePhoneEntity;
import com.cdc.inlog.pe.repository.TypePhoneRepository;
import com.cdc.inlog.pe.service.TypePhoneService;
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
public class TypePhoneServiceImpl implements TypePhoneService {

    @Autowired
    private TypePhoneRepository typePhoneRepository;

    @Override
    public List<TypePhoneEntity> getAllEntity() {
        log.info("TypePhoneServiceImpl.getAllEntity");
        return typePhoneRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_PHONE));
    }

    @Override
    public List<TypePhoneEntity> getAllEntityActivated() {
        log.info("TypePhoneServiceImpl.getAllEntityActivated");
        return typePhoneRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_PHONE))
                .stream()
                .filter(TypePhoneEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypePhoneEntity> getAllEntityDeactivated() {
        log.info("TypePhoneServiceImpl.getAllEntityDeactivated");
        return typePhoneRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_PHONE))
                .stream()
                .filter(typePhoneEntity -> !typePhoneEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public TypePhoneEntity getAllEntityById(Integer id) {
        log.info("TypePhoneServiceImpl.getAllEntityById");
        log.info("TypePhoneServiceImpl.getAllEntityById.id: " + id);
        return typePhoneRepository.findById(id)
                .orElse(new TypePhoneEntity());
    }

    @Override
    public TypePhoneEntity registerEntity(TypePhoneEntity typePhoneEntity) {
        log.info("TypePhoneServiceImpl.registerEntity");
        return typePhoneRepository.save(typePhoneEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("TypePhoneServiceImpl.existsEntityById");
        log.info("TypePhoneServiceImpl.existsEntityById.id: " + id);
        return typePhoneRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(TypePhoneEntity typePhoneEntity) {
        log.info("TypePhoneServiceImpl.updateEntityById");
        log.info("TypePhoneServiceImpl.updateEntityById.id: " + typePhoneEntity.getIdTypePhone());
        if (typePhoneRepository.existsById(typePhoneEntity.getIdTypePhone())) {
            return typePhoneRepository.updateTypePhoneEntityTypePhoneByIdTypePhone(
                    typePhoneEntity.getTypePhone(),
                    typePhoneEntity.getIdTypePhone())  == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("TypePhoneServiceImpl.checkActiveById");
        log.info("TypePhoneServiceImpl.checkActiveById.id: " + id);
        if (!typePhoneRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return typePhoneRepository.getActiveOfTypePhoneEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("TypePhoneServiceImpl.activateEntityById");
        log.info("TypePhoneServiceImpl.activateEntityById.id: " + id);
        return typePhoneRepository.updateActiveOfTypePhoneEntityById(Boolean.TRUE, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("TypePhoneServiceImpl.deactivateEntityById");
        log.info("TypePhoneServiceImpl.deactivateEntityById.id: " + id);
        return typePhoneRepository.updateActiveOfTypePhoneEntityById(Boolean.FALSE, id) == NUMBER_ONE;
    }

}