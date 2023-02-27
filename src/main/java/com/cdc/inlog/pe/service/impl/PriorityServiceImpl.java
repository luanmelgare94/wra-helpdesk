package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.WORD_PRIORITY;
import com.cdc.inlog.pe.entity.PriorityEntity;
import com.cdc.inlog.pe.repository.PriorityRepository;
import com.cdc.inlog.pe.service.PriorityService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public List<PriorityEntity> getAllEntity() {
        log.info("PriorityServiceImpl.getAllEntity");
        return priorityRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_PRIORITY));
    }

    @Override
    public List<PriorityEntity> getAllEntityActivated() {
        log.info("PriorityServiceImpl.getAllEntityActivated");
        return priorityRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_PRIORITY))
                .stream()
                .filter(PriorityEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriorityEntity> getAllEntityDeactivated() {
        log.info("PriorityServiceImpl.getAllEntityDeactivated");
        return priorityRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_PRIORITY))
                .stream()
                .filter(priorityEntity -> !priorityEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public PriorityEntity getAllEntityById(Integer id) {
        log.info("PriorityServiceImpl.getAllEntityById");
        log.info("PriorityServiceImpl.getAllEntityById.id: " + id);
        return priorityRepository.findById(id)
                .orElse(new PriorityEntity());
    }

    @Override
    public PriorityEntity registerEntity(PriorityEntity priorityEntity) {
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        return false;
    }

    @Override
    public Integer updateEntityById(PriorityEntity priorityEntity) {
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