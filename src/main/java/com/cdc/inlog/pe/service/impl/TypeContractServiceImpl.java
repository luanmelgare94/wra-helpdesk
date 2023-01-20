package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_CONTRACT;
import com.cdc.inlog.pe.entity.TypeContractEntity;
import com.cdc.inlog.pe.repository.TypeContractRepository;
import com.cdc.inlog.pe.service.TypeContractService;
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
public class TypeContractServiceImpl implements TypeContractService {

    @Autowired
    private TypeContractRepository typeContractRepository;

    @Override
    public List<TypeContractEntity> getAllEntity() {
        log.info("TypeContractServiceImpl.getAllEntity");
        return typeContractRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_CONTRACT));
    }

    @Override
    public List<TypeContractEntity> getAllEntityActivated() {
        log.info("TypeContractServiceImpl.getAllEntityActivated");
        return typeContractRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_CONTRACT))
                .stream()
                .filter(TypeContractEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypeContractEntity> getAllEntityDeactivated() {
        log.info("TypeContractServiceImpl.getAllEntityDeactivated");
        return typeContractRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_CONTRACT))
                .stream()
                .filter(typeContractEntity -> !typeContractEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public TypeContractEntity getAllEntityById(Integer id) {
        log.info("TypeContractServiceImpl.getAllEntityById");
        log.info("TypeContractServiceImpl.getAllEntityById.id: " + id);
        return typeContractRepository.findById(id)
                .orElse(new TypeContractEntity());
    }

    @Override
    public TypeContractEntity registerEntity(TypeContractEntity typeContractEntity) {
        log.info("TypeContractServiceImpl.registerEntity");
        return typeContractRepository.save(typeContractEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("TypeContractServiceImpl.existsEntityById");
        log.info("TypeContractServiceImpl.existsEntityById.id: " + id);
        return typeContractRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(TypeContractEntity typeContractEntity) {
        log.info("TypeContractServiceImpl.updateEntityById");
        log.info("TypeContractServiceImpl.updateEntityById.id: " + typeContractEntity.getIdTypeContract());
        if (typeContractRepository.existsById(typeContractEntity.getIdTypeContract())) {
            return typeContractRepository.updateTypeContractEntityTypeContractByIdTypeContract(
                    typeContractEntity.getTypeContract(),
                    typeContractEntity.getIdTypeContract()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("TypeContractServiceImpl.checkActiveById");
        log.info("TypeContractServiceImpl.checkActiveById.id: " + id);
        if (!typeContractRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return typeContractRepository.getActiveOfTypeContractEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("TypeContractServiceImpl.activateEntityById");
        log.info("TypeContractServiceImpl.activateEntityById.id: " + id);
        return typeContractRepository.updateActiveOfTypeContractEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("TypeContractServiceImpl.deactivateEntityById");
        log.info("TypeContractServiceImpl.deactivateEntityById.id: " + id);
        return typeContractRepository.updateActiveOfTypeContractEntityById(false, id) == NUMBER_ONE;
    }

}