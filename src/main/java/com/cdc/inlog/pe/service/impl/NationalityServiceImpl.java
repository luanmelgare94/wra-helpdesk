package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.WORD_COUNTRY;
import com.cdc.inlog.pe.entity.NationalityEntity;
import com.cdc.inlog.pe.repository.NationalityRepository;
import com.cdc.inlog.pe.service.NationalityService;
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
public class NationalityServiceImpl implements NationalityService {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Override
    public List<NationalityEntity> getAllEntity() {
        log.info("NationalityServiceImpl.getAllEntity");
        return nationalityRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_COUNTRY));
    }

    @Override
    public List<NationalityEntity> getAllEntityActivated() {
        log.info("NationalityServiceImpl.getAllEntityActivated");
        return nationalityRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_COUNTRY))
                .stream()
                .filter(NationalityEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<NationalityEntity> getAllEntityDeactivated() {
        log.info("NationalityServiceImpl.getAllEntityDeactivated");
        return nationalityRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_COUNTRY))
                .stream()
                .filter(nationalityEntity -> !nationalityEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public NationalityEntity getAllEntityById(Integer id) {
        log.info("NationalityServiceImpl.getAllEntityById");
        log.info("NationalityServiceImpl.getAllEntityById.id: " + id);
        return nationalityRepository.findById(id)
                .orElse(new NationalityEntity());
    }

    @Override
    public NationalityEntity registerEntity(NationalityEntity nationalityEntity) {
        log.info("NationalityServiceImpl.registerEntity");
        return nationalityRepository.save(nationalityEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("NationalityServiceImpl.existsEntityById");
        log.info("NationalityServiceImpl.existsEntityById.id: " + id);
        return nationalityRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(NationalityEntity nationalityEntity) {
        log.info("NationalityServiceImpl.updateEntityById");
        log.info("NationalityServiceImpl.updateEntityById.id: " + nationalityEntity.getIdNationality());
        if (nationalityRepository.existsById(nationalityEntity.getIdNationality())) {
            return nationalityRepository.updateNationalityEntityIsoAndCountryByIdNationality(
                    nationalityEntity.getIso(),
                    nationalityEntity.getCountry(),
                    nationalityEntity.getIdNationality()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("NationalityServiceImpl.checkActiveById");
        log.info("NationalityServiceImpl.checkActiveById.id: " + id);
        if (!nationalityRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return nationalityRepository.getActiveOfNationalityEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("NationalityServiceImpl.activateEntityById");
        log.info("NationalityServiceImpl.activateEntityById.id: " + id);
        return nationalityRepository.updateActiveOfNationalityEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("NationalityServiceImpl.deactivateEntityById");
        log.info("NationalityServiceImpl.deactivateEntityById.id: " + id);
        return nationalityRepository.updateActiveOfNationalityEntityById(false, id) == NUMBER_ONE;
    }

}