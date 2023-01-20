package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_ID_DETAIL_TICKET;
import com.cdc.inlog.pe.entity.DetailTicketEntity;
import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.repository.DetailTicketRepository;
import com.cdc.inlog.pe.repository.UsernameRepository;
import com.cdc.inlog.pe.service.DetailTicketService;
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
public class DetailTicketServiceImpl implements DetailTicketService {

    @Autowired
    private DetailTicketRepository detailTicketRepository;

    @Autowired
    private UsernameRepository usernameRepository;

    @Override
    public List<DetailTicketEntity> getAllEntity() {
        log.info("DetailTicketServiceImpl.getAllEntity");
        return detailTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_DETAIL_TICKET));
    }

    @Override
    public List<DetailTicketEntity> getAllEntityActivated() {
        log.info("DetailTicketServiceImpl.getAllEntityActivated");
        return detailTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_DETAIL_TICKET))
                .stream()
                .filter(DetailTicketEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailTicketEntity> getAllEntityDeactivated() {
        log.info("DetailTicketServiceImpl.getAllEntityDeactivated");
        return detailTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_DETAIL_TICKET))
                .stream()
                .filter(detailTicketEntity -> !detailTicketEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public DetailTicketEntity getAllEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.getAllEntityById");
        log.info("DetailTicketServiceImpl.getAllEntityById.idDetailTicket: " + id);
        return detailTicketRepository.findById(id)
                .orElse(new DetailTicketEntity());
    }

    @Override
    public DetailTicketEntity registerEntity(DetailTicketEntity detailTicketEntity) {
        log.info("DetailTicketServiceImpl.registerEntity");
        DetailTicketEntity detailTicketEntityAux = detailTicketRepository.findById(
                detailTicketRepository.save(detailTicketEntity).getIdDetailTicket()).orElse(new DetailTicketEntity());
        detailTicketEntityAux.setUsernameEntity(usernameRepository.findById(
                detailTicketEntityAux.getUsernameEntity().getIdUsername()).orElse(new UsernameEntity()));
        return detailTicketEntityAux;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.existsEntityById");
        log.info("DetailTicketServiceImpl.existsEntityById.idDetailTicket: " + id);
        return detailTicketRepository.existsById(id);
    }

    @Override
    public Integer updateEntityById(DetailTicketEntity detailTicketEntity) {
        log.info("DetailTicketServiceImpl.updateEntityById");
        log.info("DetailTicketServiceImpl.updateEntityById.idDetailTicket: " + detailTicketEntity.getIdDetailTicket());
        return null;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("DetailTicketServiceImpl.checkActiveById");
        log.info("DetailTicketServiceImpl.checkActiveById.idDetailTicket: " + id);
        if (!detailTicketRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return detailTicketRepository.getActiveOfDetailTicketEntityByIdDetailTicket(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.activateEntityById");
        log.info("DetailTicketServiceImpl.activateEntityById.idDetailTicket: " + id);
        return detailTicketRepository.updateActiveOfDetailTicketEntityByIdDetailTicket(Boolean.TRUE, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.deactivateEntityById");
        log.info("DetailTicketServiceImpl.deactivateEntityById.idDetailTicket: " + id);
        return detailTicketRepository.updateActiveOfDetailTicketEntityByIdDetailTicket(Boolean.FALSE, id) == NUMBER_ONE;
    }

    @Override
    public List<DetailTicketEntity> getAllDetailTicketEntityActivateByIdStatusTicket(Integer idStatusTicket) {
        log.info("DetailTicketServiceImpl.getAllDetailTicketEntityActivateByIdTypeTicket");
        log.info("DetailTicketServiceImpl.getAllDetailTicketEntityActivateByIdTypeTicket.idStatusTicket" + idStatusTicket);
        return detailTicketRepository.getDetailTicketEntityByIdStatusTicketAndActive(idStatusTicket, Boolean.TRUE);
    }

}