package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_ID_STATUS_TICKET;
import com.cdc.inlog.pe.entity.StatusTicketEntity;
import com.cdc.inlog.pe.repository.StatusTicketRepository;
import com.cdc.inlog.pe.repository.TicketRepository;
import com.cdc.inlog.pe.service.StatusTicketService;

import java.util.ArrayList;
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
public class StatusTicketServiceImpl implements StatusTicketService {

    @Autowired
    private StatusTicketRepository statusTicketRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<StatusTicketEntity> getAllEntity() {
        log.info("StatusTicketServiceImpl.getAllEntity");
        return statusTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_STATUS_TICKET));
    }

    @Override
    public List<StatusTicketEntity> getAllEntityActivated() {
        log.info("StatusTicketServiceImpl.getAllEntityActivated");
        return statusTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_STATUS_TICKET))
                .stream()
                .filter(StatusTicketEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatusTicketEntity> getAllEntityDeactivated() {
        log.info("StatusTicketServiceImpl.getAllEntityDeactivated");
        return statusTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_STATUS_TICKET))
                .stream()
                .filter(statusTicketEntity -> !statusTicketEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public StatusTicketEntity getAllEntityById(Integer id) {
        log.info("StatusTicketServiceImpl.getAllEntityById");
        log.info("StatusTicketServiceImpl.getAllEntityById.idStatusTicket: " + id);
        return statusTicketRepository.findById(id)
                .orElse(new StatusTicketEntity());
    }

    @Override
    public StatusTicketEntity registerEntity(StatusTicketEntity statusTicketEntity) {
        log.info("StatusTicketServiceImpl.registerEntity");
        return statusTicketRepository.save(statusTicketEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("StatusTicketServiceImpl.existsEntityById");
        log.info("StatusTicketServiceImpl.existsEntityById.idStatusTicket: " + id);
        return statusTicketRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(StatusTicketEntity statusTicketEntity) {
        log.info("StatusTicketServiceImpl.updateEntityById");
        log.info("StatusTicketServiceImpl.updateEntityById.idStatusTicket: " + statusTicketEntity.getIdStatusTicket());
        if (!statusTicketRepository.existsById(statusTicketEntity.getIdStatusTicket())) {
            return statusTicketRepository.updateStatusTicketEntityStatusTicketAndCodeColorByIdTypePhone(
                    statusTicketEntity.getStatusTicket(),
                    statusTicketEntity.getCodeColor(),
                    statusTicketEntity.getIdStatusTicket()).equals(NUMBER_ONE) ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("StatusTicketServiceImpl.checkActiveById");
        log.info("StatusTicketServiceImpl.checkActiveById.idStatusTicket: " + id);
        if (!statusTicketRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return statusTicketRepository.getActiveOfStatusTicketEntityByIdStatusTicket(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("StatusTicketServiceImpl.activateEntityById");
        log.info("StatusTicketServiceImpl.activateEntityById.idStatusTicket: " + id);
        return statusTicketRepository.updateActiveOfStatusTicketEntityByIdStatusTicket(Boolean.TRUE, id)
                .equals(NUMBER_ONE);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("StatusTicketServiceImpl.deactivateEntityById");
        log.info("StatusTicketServiceImpl.deactivateEntityById.idStatusTicket: " + id);
        return statusTicketRepository.updateActiveOfStatusTicketEntityByIdStatusTicket(Boolean.FALSE, id)
                .equals(NUMBER_ONE);
    }

    @Override
    public List<StatusTicketEntity> getAllStatusTicketActivatedByIdTicket(Integer idTicket) {
        log.info("StatusTicketServiceImpl.getAllStatusTicketActivatedByIdTicket");
        log.info("StatusTicketServiceImpl.getAllStatusTicketActivatedByIdTicket.idTicket: " + idTicket);
        return ticketRepository.existsById(idTicket) ?
                statusTicketRepository.getStatusTicketByIdTicket(idTicket) : new ArrayList<>();
    }
}