package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.WORD_ID_TICKET;
import com.cdc.inlog.pe.entity.TicketEntity;
import com.cdc.inlog.pe.entity.TypeTicketEntity;
import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.repository.*;
import com.cdc.inlog.pe.service.TicketService;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

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
        ticketEntityAux.setTypeTicketEntity(typeTicketRepository.findById(
                ticketEntityAux.getTypeTicketEntity().getIdTypeTicket()).orElse(new TypeTicketEntity()));
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
            return ticketRepository.updateTicketEntityIdTypeTicketAndDescriptionAndObservationByIdTicket(
                    ticketEntity.getTypeTicketEntity().getIdTypeTicket(),
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
    public Page<TicketEntity> getAllEntityDeactivated(Pageable pageable) {
        log.info("TicketServiceImpl.getAllEntityDeactivated");
        log.info("TicketServiceImpl.getAllEntityDeactivated.pageNumber: " + pageable.getPageNumber());
        log.info("TicketServiceImpl.getAllEntityDeactivated.pageSize: " + pageable.getPageSize());
        return ticketRepository.findByActive(Boolean.FALSE,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_ID_TICKET).ascending()));
    }

}