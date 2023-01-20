package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_TICKET;
import com.cdc.inlog.pe.entity.TypeTicketEntity;
import com.cdc.inlog.pe.repository.TypeTicketRepository;
import com.cdc.inlog.pe.service.TypeTicketService;
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
public class TypeTicketServiceImpl implements TypeTicketService {

    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @Override
    public List<TypeTicketEntity> getAllEntity() {
        log.info("TypeTicketServiceImpl.getAllEntity");
        return typeTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_TICKET));
    }

    @Override
    public List<TypeTicketEntity> getAllEntityActivated() {
        log.info("TypeTicketServiceImpl.getAllEntityActivated");
        return typeTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_TICKET))
                .stream()
                .filter(TypeTicketEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypeTicketEntity> getAllEntityDeactivated() {
        log.info("TypeTicketServiceImpl.getAllEntityDeactivated");
        return typeTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_TICKET))
                .stream()
                .filter(typeTicketEntity -> !typeTicketEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public TypeTicketEntity getAllEntityById(Integer id) {
        log.info("TypeTicketServiceImpl.getAllEntityById");
        log.info("TypeTicketServiceImpl.getAllEntityById.idTypeTicket: " + id);
        return typeTicketRepository.findById(id)
                .orElse(new TypeTicketEntity());
    }

    @Override
    public TypeTicketEntity registerEntity(TypeTicketEntity typeTicketEntity) {
        log.info("TypeTicketServiceImpl.registerEntity");
        return typeTicketRepository.save(typeTicketEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("TypeTicketServiceImpl.existsEntityById");
        log.info("TypeTicketServiceImpl.existsEntityById.idTypeTicket: " + id);
        return typeTicketRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(TypeTicketEntity typeTicketEntity) {
        log.info("TypeTicketServiceImpl.updateEntityById");
        log.info("TypeTicketServiceImpl.updateEntityById.idTypeTicket: " + typeTicketEntity.getIdTypeTicket());
        if (!typeTicketRepository.existsById(typeTicketEntity.getIdTypeTicket())) {
            return typeTicketRepository.updateTypeTicketEntityTypeTicketByIdTypeTicket(
                    typeTicketEntity.getTypeTicket(),
                    typeTicketEntity.getIdTypeTicket()).equals(NUMBER_ONE) ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("TypeTicketServiceImpl.checkActiveById");
        log.info("TypeTicketServiceImpl.checkActiveById.idTypeTicket: " + id);
        if (!typeTicketRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return typeTicketRepository.getActiveOfTypeTicketEntityByIdTypeTicket(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("TypeTicketServiceImpl.activateEntityById");
        log.info("TypeTicketServiceImpl.activateEntityById.idTypeTicket: " + id);
        return typeTicketRepository.updateActiveOfTypeTicketEntityByIdTypeTicket(Boolean.TRUE, id).equals(NUMBER_ONE);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("TypeTicketServiceImpl.deactivateEntityById");
        log.info("TypeTicketServiceImpl.deactivateEntityById.idTypeTicket: " + id);
        return typeTicketRepository.updateActiveOfTypeTicketEntityByIdTypeTicket(Boolean.FALSE, id).equals(NUMBER_ONE);
    }

}