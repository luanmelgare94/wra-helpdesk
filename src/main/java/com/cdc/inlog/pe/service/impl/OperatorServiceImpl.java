package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_OPERATOR;
import com.cdc.inlog.pe.entity.OperatorEntity;
import com.cdc.inlog.pe.repository.OperatorRepository;
import com.cdc.inlog.pe.service.OperatorService;
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
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public List<OperatorEntity> getAllEntity() {
        log.info("OperatorServiceImpl.getAllEntity");
        return operatorRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_OPERATOR));
    }

    @Override
    public List<OperatorEntity> getAllEntityActivated() {
        log.info("OperatorServiceImpl.getAllEntityActivated");
        return operatorRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_OPERATOR))
                .stream()
                .filter(OperatorEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<OperatorEntity> getAllEntityDeactivated() {
        log.info("OperatorServiceImpl.getAllEntityDeactivated");
        return operatorRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_OPERATOR))
                .stream()
                .filter(operatorEntity -> !operatorEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public OperatorEntity getAllEntityById(Integer id) {
        log.info("OperatorServiceImpl.getAllEntityById");
        log.info("OperatorServiceImpl.getAllEntityById.id: " + id);
        return operatorRepository.findById(id)
                .orElse(new OperatorEntity());
    }

    @Override
    public OperatorEntity registerEntity(OperatorEntity operatorEntity) {
        log.info("OperatorServiceImpl.registerEntity");
        return operatorRepository.save(operatorEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("OperatorServiceImpl.existsEntityById");
        log.info("OperatorServiceImpl.existsEntityById.id: " + id);
        return operatorRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(OperatorEntity operatorEntity) {
        log.info("OperatorServiceImpl.updateEntityById");
        log.info("OperatorServiceImpl.updateEntityById.id: " + operatorEntity.getIdOperator());
        if (operatorRepository.existsById(operatorEntity.getIdOperator())) {
            return operatorRepository.updateOperatorEntityOperatorByIdOperator(
                    operatorEntity.getOperator(),
                    operatorEntity.getIdOperator()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("OperatorServiceImpl.checkActiveById");
        log.info("OperatorServiceImpl.checkActiveById.id: " + id);
        if (!operatorRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return operatorRepository.getActiveOfOperatorEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("OperatorServiceImpl.activateEntityById");
        log.info("OperatorServiceImpl.activateEntityById.id: " + id);
        return operatorRepository.updateActiveOfOperatorEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("OperatorServiceImpl.deactivateEntityById");
        log.info("OperatorServiceImpl.deactivateEntityById.id: " + id);
        return operatorRepository.updateActiveOfOperatorEntityById(false, id) == NUMBER_ONE;
    }

}