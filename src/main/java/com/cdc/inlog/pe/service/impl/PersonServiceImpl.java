package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_FIRST_NAME;
import com.cdc.inlog.pe.entity.PersonEntity;
import com.cdc.inlog.pe.repository.PersonRepository;
import com.cdc.inlog.pe.service.PersonService;
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
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonEntity> getAllEntity() {
        log.info("PersonServiceImpl.getAllEntity");
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_FIRST_NAME));
    }

    @Override
    public List<PersonEntity> getAllEntityActivated() {
        log.info("PersonServiceImpl.getAllEntityActivated");
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_FIRST_NAME))
                .stream()
                .filter(PersonEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonEntity> getAllEntityDeactivated() {
        log.info("PersonServiceImpl.getAllEntityDeactivated");
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_FIRST_NAME))
                .stream()
                .filter(personEntity -> !personEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public PersonEntity getAllEntityById(Integer id) {
        log.info("PersonServiceImpl.getAllEntityById");
        log.info("PersonServiceImpl.getAllEntityById.id: " + id);
        return personRepository.findById(id)
                .orElse(new PersonEntity());
    }

    @Override
    public PersonEntity registerEntity(PersonEntity personEntity) {
        log.info("PersonServiceImpl.registerEntity");
        return personRepository.save(personEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("PersonServiceImpl.existsEntityById");
        log.info("PersonServiceImpl.existsEntityById.id: " + id);
        return personRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(PersonEntity personEntity) {
        log.info("PersonServiceImpl.updateEntityById");
        log.info("PersonServiceImpl.updateEntityById.id: " + personEntity.getIdPerson());
        if (personRepository.existsById(personEntity.getIdPerson())) {
            return personRepository.updatePersonEntityByIdPerson(
                    personEntity.getFirstName(),
                    personEntity.getLastName(),
                    personEntity.getSecondLastName(),
                    personEntity.getNationalityEntity().getIdNationality(),
                    personEntity.getGenderEntity().getIdGender(),
                    personEntity.getTypeDocumentEntity().getIdTypeDocument(),
                    personEntity.getDoi(),
                    personEntity.getBirthday(),
                    personEntity.getIdPerson()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("PersonServiceImpl.checkActiveById");
        log.info("PersonServiceImpl.checkActiveById.id: " + id);
        if (!personRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return personRepository.getActiveOfPersonEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("PersonServiceImpl.activateEntityById");
        log.info("PersonServiceImpl.activateEntityById.id: " + id);
        return personRepository.updateActiveOfPersonEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("PersonServiceImpl.deactivateEntityById");
        log.info("PersonServiceImpl.deactivateEntityById.id: " + id);
        return personRepository.updateActiveOfPersonEntityById(false, id) == NUMBER_ONE;
    }

    @Override
    public boolean existsPersonEntityByIdTypeDocumentAndDoi(Integer idTypeDocument, String doi) {
        log.info("PersonServiceImpl.existPersonEntityByIdTypeDocumentAndDoi");
        log.info("PersonServiceImpl.existPersonEntityByIdTypeDocumentAndDoi.idTypeDocument: " + idTypeDocument);
        log.info("PersonServiceImpl.existPersonEntityByIdTypeDocumentAndDoi.doi: " + doi);
        return !personRepository.existsPersonEntityByIdTypeDocumentAndDoi(idTypeDocument, doi).equals(NUMBER_ZERO);
    }
}