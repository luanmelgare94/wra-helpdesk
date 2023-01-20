package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_EMAIL;
import com.cdc.inlog.pe.entity.TypeEmailEntity;
import com.cdc.inlog.pe.repository.TypeEmailRepository;
import com.cdc.inlog.pe.service.TypeEmailService;
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
public class TypeEmailServiceImpl implements TypeEmailService {

    @Autowired
    private TypeEmailRepository typeEmailRepository;

    @Override
    public List<TypeEmailEntity> getAllEntity() {
        log.info("TypeEmailServiceImpl.getAllEntity");
        return typeEmailRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_EMAIL));
    }

    @Override
    public List<TypeEmailEntity> getAllEntityActivated() {
        log.info("TypeEmailServiceImpl.getAllEntityActivated");
        return typeEmailRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_EMAIL))
                .stream()
                .filter(TypeEmailEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypeEmailEntity> getAllEntityDeactivated() {
        log.info("TypeEmailServiceImpl.getAllEntityDeactivated");
        return typeEmailRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_EMAIL))
                .stream()
                .filter(typeEmailEntity -> !typeEmailEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public TypeEmailEntity getAllEntityById(Integer id) {
        log.info("TypeEmailServiceImpl.getAllEntityById");
        log.info("TypeEmailServiceImpl.getAllEntityById.id: " + id);
        return typeEmailRepository.findById(id)
                .orElse(new TypeEmailEntity());
    }

    @Override
    public TypeEmailEntity registerEntity(TypeEmailEntity typeEmailEntity) {
        log.info("TypeEmailServiceImpl.registerEntity");
        return typeEmailRepository.save(typeEmailEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("TypeEmailServiceImpl.existsEntityById");
        log.info("TypeEmailServiceImpl.existsEntityById.id: " + id);
        return typeEmailRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(TypeEmailEntity typeEmailEntity) {
        log.info("TypeEmailServiceImpl.updateEntityById");
        log.info("TypeEmailServiceImpl.updateEntityById.id: " + typeEmailEntity.getIdTypeEmail());
        if (typeEmailRepository.existsById(typeEmailEntity.getIdTypeEmail())) {
            return typeEmailRepository.updateTypeEmailEntityTypeEmailByIdTypeEmail(
                    typeEmailEntity.getTypeEmail(),
                    typeEmailEntity.getIdTypeEmail()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("TypeEmailServiceImpl.checkActiveById");
        log.info("TypeEmailServiceImpl.checkActiveById.id: " + id);
        if (!typeEmailRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return typeEmailRepository.getActiveOfTypeEmailEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("TypeEmailServiceImpl.activateEntityById");
        log.info("TypeEmailServiceImpl.activateEntityById.id: " + id);
        return typeEmailRepository.updateActiveOfTypeEmailEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("TypeEmailServiceImpl.deactivateEntityById");
        log.info("TypeEmailServiceImpl.deactivateEntityById.id: " + id);
        return typeEmailRepository.updateActiveOfTypeEmailEntityById(false, id) == NUMBER_ONE;
    }

}