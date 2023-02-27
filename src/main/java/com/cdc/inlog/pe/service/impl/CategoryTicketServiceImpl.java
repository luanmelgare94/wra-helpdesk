package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.WORD_CATEGORY_TICKET;
import com.cdc.inlog.pe.entity.CategoryTicketEntity;
import com.cdc.inlog.pe.repository.CategoryTicketRepository;
import com.cdc.inlog.pe.service.CategoryTicketService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryTicketServiceImpl implements CategoryTicketService {

    @Autowired
    private CategoryTicketRepository categoryTicketRepository;

    @Override
    public List<CategoryTicketEntity> getAllEntity() {
        log.info("CategoryTicketServiceImpl.getAllEntity");
        return categoryTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_CATEGORY_TICKET));
    }

    @Override
    public List<CategoryTicketEntity> getAllEntityActivated() {
        log.info("CategoryTicketServiceImpl.getAllEntityActivated");
        return categoryTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_CATEGORY_TICKET))
                .stream()
                .filter(CategoryTicketEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryTicketEntity> getAllEntityDeactivated() {
        log.info("CategoryTicketServiceImpl.getAllEntityDeactivated");
        return categoryTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_CATEGORY_TICKET))
                .stream()
                .filter(categoryTicketEntity -> !categoryTicketEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryTicketEntity getAllEntityById(Integer id) {
        log.info("CategoryTicketServiceImpl.getAllEntityById");
        log.info("CategoryTicketServiceImpl.getAllEntityById.id: " + id);
        return categoryTicketRepository.findById(id)
                .orElse(new CategoryTicketEntity());
    }

    @Override
    public CategoryTicketEntity registerEntity(CategoryTicketEntity categoryTicketEntity) {
        return null;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("CategoryTicketServiceImpl.existsEntityById");
        log.info("CategoryTicketServiceImpl.existsEntityById.id: " + id);
        return categoryTicketRepository.existsById(id);
    }

    @Override
    public Integer updateEntityById(CategoryTicketEntity categoryTicketEntity) {
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