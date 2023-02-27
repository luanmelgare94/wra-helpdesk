package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_USERNAME;
import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.repository.*;
import com.cdc.inlog.pe.service.UsernameService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UsernameServiceImpl implements UsernameService {

    @Autowired
    private UsernameRepository usernameRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private TypeContractRepository typeContractRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UsernameEntity> getAllEntity() {
        /*log.info("UsernameServiceImpl.getAllEntity");
        return usernameRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_USERNAME));*/
        log.info("UsernameServiceImpl.getAllEntity");
        return new ArrayList<>();
    }

    @Override
    public List<UsernameEntity> getAllEntityActivated() {
        log.info("UsernameServiceImpl.getAllEntityActivated");
        return usernameRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_USERNAME))
                .stream()
                .filter(UsernameEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsernameEntity> getAllEntityDeactivated() {
        /*log.info("UsernameServiceImpl.getAllEntityDeactivated");
        return usernameRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_USERNAME))
                .stream()
                .filter(usernameEntity -> !usernameEntity.isActive())
                .collect(Collectors.toList());*/
        log.info("UsernameServiceImpl.getAllEntityDeactivated");
        return new ArrayList<>();
    }

    @Override
    public UsernameEntity getAllEntityById(Integer id) {
        log.info("UsernameServiceImpl.getAllEntityById");
        log.info("UsernameServiceImpl.getAllEntityById.id: " + id);
        return usernameRepository.findById(id)
                .orElse(new UsernameEntity());
    }

    @Override
    public UsernameEntity registerEntity(UsernameEntity usernameEntity) {
        log.info("UsernameServiceImpl.registerEntity");
        usernameEntity.getPersonEntity()
                .setIdPerson(personRepository.save(usernameEntity.getPersonEntity()).getIdPerson());
        return usernameRepository.save(usernameEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("UsernameServiceImpl.existsEntityById");
        log.info("UsernameServiceImpl.existsEntityById.id: " + id);
        return usernameRepository.existsById(id);
    }

    @Override
    public Integer updateEntityById(UsernameEntity usernameEntity) {
        log.info("UsernameServiceImpl.updateEntityById");
        log.info("UsernameServiceImpl.updateEntityById.id: " + usernameEntity.getIdUsername());
        return NUMBER_ZERO;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("UsernameServiceImpl.checkActiveById");
        log.info("UsernameServiceImpl.checkActiveById.id: " + id);
        if (!usernameRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return usernameRepository.getActiveOfUsernameEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("UsernameServiceImpl.activateEntityById");
        log.info("UsernameServiceImpl.activateEntityById.id: " + id);
        Integer idPerson = usernameRepository.getIdPersonOfUsernameEntityByIdUsername(id);
        log.info("UsernameServiceImpl.activateEntityById.idPerson: " + idPerson);
        Integer resultUsername = usernameRepository.updateActiveOfUsernameEntityById(Boolean.TRUE, id);
        Integer resultPerson = personRepository.updateActiveOfPersonEntityById(Boolean.TRUE, idPerson);
        Integer resultEmail = emailRepository.updateUserActiveOfEmailEntityByIdPerson(Boolean.TRUE, idPerson);
        Integer resultPhone = phoneRepository.updateUserActiveOfPhoneEntityByIdPerson(Boolean.TRUE, idPerson);
        log.info("UsernameServiceImpl.activateEntityById.resultUsername: " + resultUsername);
        log.info("UsernameServiceImpl.activateEntityById.resultPerson: " + resultPerson);
        log.info("UsernameServiceImpl.activateEntityById.resultEmail: " + resultEmail);
        log.info("UsernameServiceImpl.activateEntityById.resultPhone: " + resultPhone);
        return !resultUsername.equals(NUMBER_ZERO) && !resultEmail.equals(NUMBER_ZERO) &&
                !resultPhone.equals(NUMBER_ZERO) && !resultPerson.equals(NUMBER_ZERO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("UsernameServiceImpl.deactivateEntityById");
        log.info("UsernameServiceImpl.deactivateEntityById.id: " + id);
        Integer idPerson = usernameRepository.getIdPersonOfUsernameEntityByIdUsername(id);
        log.info("UsernameServiceImpl.deactivateEntityById.idPerson: " + idPerson);
        Integer resultUsername = usernameRepository.updateActiveOfUsernameEntityById(Boolean.FALSE, id);
        Integer resultPerson = personRepository.updateActiveOfPersonEntityById(Boolean.FALSE, idPerson);
        Integer resultEmail = emailRepository.updateUserActiveOfEmailEntityByIdPerson(Boolean.FALSE, idPerson);
        Integer resultPhone = phoneRepository.updateUserActiveOfPhoneEntityByIdPerson(Boolean.FALSE, idPerson);
        log.info("UsernameServiceImpl.deactivateEntityById.resultUsername: " + resultUsername);
        log.info("UsernameServiceImpl.deactivateEntityById.resultPerson: " + resultPerson);
        log.info("UsernameServiceImpl.deactivateEntityById.resultEmail: " + resultEmail);
        log.info("UsernameServiceImpl.deactivateEntityById.resultPhone: " + resultPhone);
        return !resultUsername.equals(NUMBER_ZERO) && !resultEmail.equals(NUMBER_ZERO) &&
                !resultPhone.equals(NUMBER_ZERO) && !resultPerson.equals(NUMBER_ZERO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateUsernameEntityPasswdByIdUsername(String passwd, String username) {
        log.info("UsernameServiceImpl.updateUsernameEntityPasswdByIdUsername");
        log.info("UsernameServiceImpl.updateUsernameEntityPasswdByIdUsername.username: " + username);
        log.info("UsernameServiceImpl.updateUsernameEntityPasswdByIdUsername.passwd: " + passwd);
        if (usernameRepository.existsUsernameEntityByUsername(username)) {
            return usernameRepository.updateUsernameEntityPasswdByIdUsername(
                    passwd, username).equals(NUMBER_ONE) ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Page<UsernameEntity> getAllEntity(Pageable pageable) {
        log.info("UsernameServiceImpl.getAllEntity");
        log.info("UsernameServiceImpl.getAllEntity.pageNumber: " + pageable.getPageNumber());
        log.info("UsernameServiceImpl.getAllEntity.pageSize: " + pageable.getPageSize());
        return usernameRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_USERNAME).ascending()));
    }

    @Override
    public Page<UsernameEntity> getAllEntityActivated(Pageable pageable) {
        log.info("UsernameServiceImpl.getAllEntityActivated");
        log.info("UsernameServiceImpl.getAllEntityActivated.pageNumber: " + pageable.getPageNumber());
        log.info("UsernameServiceImpl.getAllEntityActivated.pageSize: " + pageable.getPageSize());
        return usernameRepository.findByActive(Boolean.TRUE,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_USERNAME).ascending()));
    }

    @Override
    public Page<UsernameEntity> getAllEntityDeactivated(Pageable pageable) {
        log.info("UsernameServiceImpl.getAllEntityDeactivated");
        log.info("UsernameServiceImpl.getAllEntityDeactivated.pageNumber: " + pageable.getPageNumber());
        log.info("UsernameServiceImpl.getAllEntityDeactivated.pageSize: " + pageable.getPageSize());
        return usernameRepository.findByActive(Boolean.FALSE,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(WORD_USERNAME).ascending()));
    }

    @Override
    public List<UsernameEntity> getAllEntityActivatedByIdRole(Integer idRole) {
        log.info("UsernameServiceImpl.getAllEntityActivatedByIdRole");
        log.info("UsernameServiceImpl.getAllEntityActivatedByIdRole.idRole: " + idRole);
        return usernameRepository.getUsernameEntityByActiveAndRole(Boolean.TRUE, idRole);
    }

}