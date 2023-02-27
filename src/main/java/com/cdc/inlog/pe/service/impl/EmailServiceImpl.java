package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.WORD_EMAIL;
import com.cdc.inlog.pe.entity.EmailEntity;
import com.cdc.inlog.pe.entity.PersonEntity;
import com.cdc.inlog.pe.repository.EmailRepository;
import com.cdc.inlog.pe.repository.PersonRepository;
import com.cdc.inlog.pe.service.EmailService;
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
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<EmailEntity> getAllEntity() {
        log.info("EmailServiceImpl.getAllEntity");
        return emailRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_EMAIL));
    }

    @Override
    public List<EmailEntity> getAllEntityActivated() {
        log.info("EmailServiceImpl.getAllEntityActivated");
        return emailRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_EMAIL))
                .stream()
                .filter(emailEntity -> emailEntity.isActive() && emailEntity.isUserActive())
                .collect(Collectors.toList());
    }

    @Override
    public List<EmailEntity> getAllEntityDeactivated() {
        log.info("EmailServiceImpl.getAllEntityDeactivated");
        return emailRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_EMAIL))
                .stream()
                .filter(emailEntity -> !emailEntity.isActive() || !emailEntity.isUserActive())
                .collect(Collectors.toList());
    }

    @Override
    public EmailEntity getAllEntityById(Integer id) {
        log.info("EmailServiceImpl.getAllEntityById");
        log.info("EmailServiceImpl.getAllEntityById.id: " + id);
        return emailRepository.findById(id)
                .orElse(new EmailEntity());
    }

    @Override
    public EmailEntity registerEntity(EmailEntity emailEntity) {
        log.info("EmailServiceImpl.registerEntity");
        EmailEntity emailEntityAux = emailRepository.findById(emailRepository.save(emailEntity).getIdEmail())
                .orElse(new EmailEntity());
        emailEntityAux.setPersonEntity(personRepository.findById(emailEntityAux.getPersonEntity().getIdPerson())
                .orElse(new PersonEntity()));
        return emailEntityAux;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("EmailServiceImpl.existsEntityById");
        log.info("EmailServiceImpl.existsEntityById.id: " + id);
        return emailRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(EmailEntity emailEntity) {
        log.info("EmailServiceImpl.updateEntityById");
        log.info("EmailServiceImpl.updateEntityById.id: " + emailEntity.getIdEmail());
        if (emailRepository.existsById(emailEntity.getIdEmail())) {
            return emailRepository.updateEmailEntityIdTypeEmailAndEmailByIdEmail(
                    emailEntity.getTypeEmailEntity().getIdTypeEmail(),
                    emailEntity.getEmail(),
                    emailEntity.getIdEmail()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("EmailServiceImpl.checkActiveById");
        log.info("EmailServiceImpl.checkActiveById.id: " + id);
        if (!emailRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return emailRepository.getActiveOfEmailEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("EmailServiceImpl.activateEntityById");
        log.info("EmailServiceImpl.activateEntityById.id: " + id);
        return emailRepository.updateActiveOfEmailEntityById(Boolean.TRUE, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("EmailServiceImpl.deactivateEntityById");
        log.info("EmailServiceImpl.deactivateEntityById.id: " + id);
        return emailRepository.updateActiveOfEmailEntityById(Boolean.FALSE, id) == NUMBER_ONE;
    }

    @Override
    public List<EmailEntity> getAllEmailEntityActivatedByIdPerson(Integer idPerson) {
        log.info("EmailServiceImpl.getAllEmailEntityActivatedByIdPerson");
        log.info("EmailServiceImpl.getAllEmailEntityActivatedByIdPerson.idPerson: " + idPerson);
        return emailRepository.getAllEmailEntityByIdPersonAndActive(idPerson, Boolean.TRUE);
    }

    @Override
    public List<EmailEntity> getAllEmailEntityDeactivatedByIdPerson(Integer idPerson) {
        log.info("EmailServiceImpl.getAllEmailEntityDeactivatedByIdPerson");
        log.info("EmailServiceImpl.getAllEmailEntityDeactivatedByIdPerson.idPerson: " + idPerson);
        return emailRepository.getAllEmailEntityByIdPersonAndActive(idPerson, Boolean.FALSE);
    }

}