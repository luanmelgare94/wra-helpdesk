package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_PHONE;
import com.cdc.inlog.pe.entity.PhoneEntity;
import com.cdc.inlog.pe.repository.PhoneRepository;
import com.cdc.inlog.pe.service.PhoneService;
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
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<PhoneEntity> getAllEntity() {
        log.info("PhoneServiceImpl.getAllEntity");
        return phoneRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_PHONE));
    }

    @Override
    public List<PhoneEntity> getAllEntityActivated() {
        log.info("PhoneServiceImpl.getAllEntityActivated");
        return phoneRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_PHONE))
                .stream()
                .filter(phoneEntity -> phoneEntity.isActive() && phoneEntity.isUserActive())
                .collect(Collectors.toList());
    }

    @Override
    public List<PhoneEntity> getAllEntityDeactivated() {
        log.info("PhoneServiceImpl.getAllEntityDeactivated");
        return phoneRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_PHONE))
                .stream()
                .filter(phoneEntity -> !phoneEntity.isActive() || !phoneEntity.isUserActive())
                .collect(Collectors.toList());
    }

    @Override
    public PhoneEntity getAllEntityById(Integer id) {
        log.info("PhoneServiceImpl.getAllEntityById");
        log.info("PhoneServiceImpl.getAllEntityById.id: " + id);
        return phoneRepository.findById(id)
                .orElse(new PhoneEntity());
    }

    @Override
    public PhoneEntity registerEntity(PhoneEntity phoneEntity) {
        log.info("PhoneServiceImpl.registerEntity");
        return phoneRepository.save(phoneEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("PhoneServiceImpl.existsEntityById");
        log.info("PhoneServiceImpl.existsEntityById.id: " + id);
        return phoneRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(PhoneEntity phoneEntity) {
        log.info("PhoneServiceImpl.updateEntityById");
        log.info("PhoneServiceImpl.updateEntityById.id: " + phoneEntity.getIdPhone());
        if (phoneRepository.existsById(phoneEntity.getIdPhone())) {
            return phoneRepository.updatePhoneEntityIdTypePhoneAndIdOperatorAndPhoneByIdPhone(
                    phoneEntity.getTypePhoneEntity().getIdTypePhone(),
                    phoneEntity.getOperatorEntity().getIdOperator(),
                    phoneEntity.getPhone(),
                    phoneEntity.getIdPhone()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("PhoneServiceImpl.checkActiveById");
        log.info("PhoneServiceImpl.checkActiveById.id: " + id);
        if (!phoneRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return phoneRepository.getActiveOfPhoneEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("PhoneServiceImpl.activateEntityById");
        log.info("PhoneServiceImpl.activateEntityById.id: " + id);
        return phoneRepository.updateActiveOfPhoneEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("PhoneServiceImpl.deactivateEntityById");
        log.info("PhoneServiceImpl.deactivateEntityById.id: " + id);
        return phoneRepository.updateActiveOfPhoneEntityById(false, id) == NUMBER_ONE;
    }

}