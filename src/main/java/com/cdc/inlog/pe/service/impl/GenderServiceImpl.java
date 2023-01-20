package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_GENDER;
import com.cdc.inlog.pe.entity.GenderEntity;
import com.cdc.inlog.pe.repository.GenderRepository;
import com.cdc.inlog.pe.service.GenderService;
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
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public List<GenderEntity> getAllEntity() {
        log.info("GenderServiceImpl.getAllEntity");
        return genderRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_GENDER));
    }

    @Override
    public List<GenderEntity> getAllEntityActivated() {
        log.info("GenderServiceImpl.getAllEntityActivated");
        return genderRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_GENDER))
                .stream()
                .filter(GenderEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<GenderEntity> getAllEntityDeactivated() {
        log.info("GenderServiceImpl.getAllEntityDeactivated");
        return genderRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_GENDER))
                .stream()
                .filter(genderEntity -> !genderEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public GenderEntity getAllEntityById(Integer id) {
        log.info("GenderServiceImpl.getAllEntityById");
        log.info("GenderServiceImpl.getAllEntityById.id: " + id);
        return genderRepository.findById(id)
                .orElse(new GenderEntity());
    }

    @Override
    public GenderEntity registerEntity(GenderEntity genderEntity) {
        log.info("GenderServiceImpl.registerEntity");
        return genderRepository.save(genderEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("GenderServiceImpl.existsEntityById");
        log.info("GenderServiceImpl.existsEntityById.id: " + id);
        return genderRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(GenderEntity genderEntity) {
        log.info("GenderServiceImpl.updateEntityById");
        log.info("GenderServiceImpl.updateEntityById.id: " + genderEntity.getIdGender());
        if (genderRepository.existsById(genderEntity.getIdGender())) {
            return genderRepository.updateGenderEntityIsoAndGenderByIdGender(
                    genderEntity.getIso(),
                    genderEntity.getGender(),
                    genderEntity.getIdGender()) != NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("GenderServiceImpl.checkActiveById");
        log.info("GenderServiceImpl.checkActiveById.id: " + id);
        if (!genderRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return genderRepository.getActiveOfGenderEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("GenderServiceImpl.activateEntityById");
        log.info("GenderServiceImpl.activateEntityById.id: " + id);
        return genderRepository.updateActiveOfGenderEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("GenderServiceImpl.deactivateEntityById");
        log.info("GenderServiceImpl.deactivateEntityById.id: " + id);
        return genderRepository.updateActiveOfGenderEntityById(false, id) == NUMBER_ONE;
    }

}