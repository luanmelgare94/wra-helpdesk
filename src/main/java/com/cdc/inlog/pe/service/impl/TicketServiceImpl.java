package com.cdc.inlog.pe.service.impl;

import com.cdc.inlog.pe.entity.*;
import com.cdc.inlog.pe.repository.*;
import com.cdc.inlog.pe.service.TicketService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.cdc.inlog.pe.service.UsernameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.cdc.inlog.pe.util.Constants.*;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryTicketRepository categoryTicketRepository;

    @Autowired
    private DetailTicketRepository detailTicketRepository;

    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @Autowired
    private StatusTicketRepository statusTicketRepository;

    @Autowired
    private UsernameRepository usernameRepository;

    @Override
    public List<TicketEntity> getAllEntity() {
        log.info("TicketServiceImpl.getAllEntity");
        return new ArrayList<>();
    }

    @Override
    public List<TicketEntity> getAllEntityActivated() {
        log.info("TicketServiceImpl.getAllEntityActivated");
        return new ArrayList<>();
    }

    @Override
    public List<TicketEntity> getAllEntityDeactivated() {
        log.info("TicketServiceImpl.getAllEntityDeactivated");
        return new ArrayList<>();
    }

    @Override
    public TicketEntity getAllEntityById(Integer id) {
        log.info("TicketServiceImpl.getAllEntityById");
        log.info("TicketServiceImpl.getAllEntityById.idTicketEntity: " + id);
        return ticketRepository.findById(id)
                .orElse(new TicketEntity());
    }

    @Override
    public TicketEntity registerEntity(TicketEntity ticketEntity) {
        log.info("TicketServiceImpl.registerEntity");
        TicketEntity ticketEntityAux = ticketRepository.findById(ticketRepository.save(ticketEntity).getIdTicket())
                .orElse(new TicketEntity());
        ticketEntityAux.setCategoryTicketEntity(categoryTicketRepository.findById(
                ticketEntityAux.getCategoryTicketEntity().getIdCategoryTicket()).orElse(new CategoryTicketEntity()));
        ticketEntityAux.setUsernameEntity(usernameRepository.findById(
                ticketEntityAux.getUsernameEntity().getIdUsername()).orElse(new UsernameEntity()));
        ticketEntityAux.setDetailTicketEntityList(detailTicketRepository.getDetailTicketEntityByIdTicketAndActive(
                ticketEntityAux.getIdTicket(), Boolean.TRUE));
        return ticketEntityAux;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("TicketServiceImpl.existsEntityById");
        log.info("TicketServiceImpl.existsEntityById.idTicketEntity: " + id);
        return ticketRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(TicketEntity ticketEntity) {
        log.info("TicketServiceImpl.updateEntityById");
        log.info("TicketServiceImpl.updateEntityById.idTicketEntity: " + ticketEntity.getIdTicket());
        if (ticketRepository.existsById(ticketEntity.getIdTicket())) {
            return ticketRepository.updateTicketEntityDescriptionAndObservationByIdTicket(
                    ticketEntity.getDescription(),
                    ticketEntity.getObservation(),
                    ticketEntity.getIdTicket()).equals(NUMBER_ONE) ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("TicketServiceImpl.checkActiveById");
        log.info("TicketServiceImpl.checkActiveById.idTicketEntity: " + id);
        if (!ticketRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return ticketRepository.getActiveOfTicketEntityByIdTicket(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("TicketServiceImpl.activateEntityById");
        log.info("TicketServiceImpl.activateEntityById.idTicketEntity: " + id);
        Integer resultTicket = ticketRepository.updateActiveOfTicketEntityByIdTicket(Boolean.TRUE, id);
        Integer resultDetailTicket = detailTicketRepository.updateActiveOfDetailTicketEntityByIdTicket(Boolean.TRUE, id);
        log.info("TicketServiceImpl.activateEntityById.resultTicket: " + resultTicket);
        log.info("TicketServiceImpl.activateEntityById.resultDetailTicket: " + resultDetailTicket);
        return !resultTicket.equals(NUMBER_ZERO) && !resultDetailTicket.equals(NUMBER_ZERO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("TicketServiceImpl.deactivateEntityById");
        log.info("TicketServiceImpl.deactivateEntityById.idTicketEntity: " + id);
        Integer resultTicket = ticketRepository.updateActiveOfTicketEntityByIdTicket(Boolean.FALSE, id);
        Integer resultDetailTicket = detailTicketRepository.updateActiveOfDetailTicketEntityByIdTicket(Boolean.FALSE, id);
        log.info("TicketServiceImpl.activateEntityById.resultTicket: " + resultTicket);
        log.info("TicketServiceImpl.activateEntityById.resultDetailTicket: " + resultDetailTicket);
        return !resultTicket.equals(NUMBER_ZERO) && !resultDetailTicket.equals(NUMBER_ZERO);
    }

    @Override
    public Page<TicketEntity> getAllEntity(Pageable pageable) {
        log.info("TicketServiceImpl.getAllEntity");
        log.info("TicketServiceImpl.getAllEntity.pageNumber: " + pageable.getPageNumber());
        log.info("TicketServiceImpl.getAllEntity.pageSize: " + pageable.getPageSize());
        return ticketRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_ID_TICKET).ascending()));
    }

    @Override
    public Page<TicketEntity> getAllEntityActivated(Pageable pageable) {
        log.info("TicketServiceImpl.getAllEntityActivated");
        log.info("TicketServiceImpl.getAllEntityActivated.pageNumber: " + pageable.getPageNumber());
        log.info("TicketServiceImpl.getAllEntityActivated.pageSize: " + pageable.getPageSize());
        return ticketRepository.findByActive(Boolean.TRUE,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_ID_TICKET).ascending()));
    }

    @Override
    public Page<TicketEntity> getAllEntityActivatedByUsername(Pageable pageable, String username) {
        log.info("TicketServiceImpl.getAllEntityActivatedByUsername");
        log.info("TicketServiceImpl.getAllEntityActivatedByUsername.pageNumber: " + pageable.getPageNumber());
        log.info("TicketServiceImpl.getAllEntityActivatedByUsername.pageSize: " + pageable.getPageSize());
        log.info("TicketServiceImpl.getAllEntityActivatedByUsername.username: " + username);
        return ticketRepository.findByActiveAndUsername(Boolean.TRUE, username,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_QUERY_ID_TICKET).ascending()));
    }

    @Override
    public Page<TicketEntity> getAllEntityDeactivated(Pageable pageable) {
        log.info("TicketServiceImpl.getAllEntityDeactivated");
        log.info("TicketServiceImpl.getAllEntityDeactivated.pageNumber: " + pageable.getPageNumber());
        log.info("TicketServiceImpl.getAllEntityDeactivated.pageSize: " + pageable.getPageSize());
        return ticketRepository.findByActive(Boolean.FALSE,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_ID_TICKET).ascending()));
    }

    @Override
    public Page<TicketEntity> getAllEntityDeactivatedByUsername(Pageable pageable, String username) {
        log.info("TicketServiceImpl.getAllEntityDeactivatedByUsername");
        log.info("TicketServiceImpl.getAllEntityDeactivatedByUsername.pageNumber: " + pageable.getPageNumber());
        log.info("TicketServiceImpl.getAllEntityDeactivatedByUsername.pageSize: " + pageable.getPageSize());
        log.info("TicketServiceImpl.getAllEntityDeactivatedByUsername.username: " + username);
        return ticketRepository.findByActiveAndUsername(Boolean.FALSE, username,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_QUERY_ID_TICKET).ascending()));
    }

    @Override
    public boolean isCategorizedAndPrioritized(Integer idTicket) {
        log.info("TicketServiceImpl.isCategorizedAndPrioritized");
        log.info("TicketServiceImpl.isCategorizedAndPrioritized.idTicket: " + idTicket);
        TicketEntity ticketEntity = ticketRepository.findById(idTicket).orElse(new TicketEntity());
        return !ticketEntity.getCategoryTicketEntity().getIdCategoryTicket().equals(NUMBER_NINE) &&
                !ticketEntity.getPriorityEntity().getIdPriority().equals(NUMBER_FOUR);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateEntityIdCategoryAndIdPriorityAndUserByIdTicket(Integer idTicket, Integer idCategory,
                                                                        Integer idPriority, Integer idAssignedUser,
                                                                        String monitorUser) {
        log.info("TicketServiceImpl.updateEntityIdCategoryAndIdPriority");
        log.info("TicketServiceImpl.updateEntityIdCategoryAndIdPriority.idTicket: " + idTicket);
        log.info("TicketServiceImpl.updateEntityIdCategoryAndIdPriority.idCategory: " + idCategory);
        log.info("TicketServiceImpl.updateEntityIdCategoryAndIdPriority.idPriority: " + idPriority);
        log.info("TicketServiceImpl.updateEntityIdCategoryAndIdPriority.idAssignedUser: " + idAssignedUser);
        log.info("TicketServiceImpl.updateEntityIdCategoryAndIdPriority.monitorUser: " + monitorUser);
        if (!ticketRepository
                .updateTicketEntityIdCategoryAndIdPriorityAndUsernameAndDateLastUpdateByIdTicket(idCategory, idPriority,
                        monitorUser, LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU)), idTicket)
                .equals(NUMBER_ZERO)) {
            DetailTicketEntity detailTicketEntity = new DetailTicketEntity();
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setIdTicket(idTicket);
            detailTicketEntity.setTicketEntity(ticketEntity);
            StatusTicketEntity statusTicketEntity = new StatusTicketEntity();
            statusTicketEntity.setIdStatusTicket(2);
            detailTicketEntity.setStatusTicketEntity(statusTicketEntity);
            detailTicketEntity.setActive(true);
            detailTicketEntity.setDescription("TICKET ABIERTO");
            detailTicketEntity.setObservation("SIN OBSERVACIONES");
            UsernameEntity usernameEntity = new UsernameEntity();
            usernameEntity.setIdUsername(idAssignedUser);
            detailTicketEntity.setUsernameEntity(usernameEntity);
            detailTicketEntity.setDateRegister(LocalDateTime.now());
            detailTicketRepository.save(detailTicketEntity);
            return true;
        } else {
            return false;
        }
    }

}