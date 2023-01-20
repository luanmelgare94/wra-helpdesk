package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_PERSON;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_EMPTY;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED;
import com.cdc.inlog.pe.dto.person.PersonDefaultDto;
import com.cdc.inlog.pe.dto.person.PersonResponseByIdDto;
import com.cdc.inlog.pe.mapper.PersonMapper;
import com.cdc.inlog.pe.service.PersonService;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = API_PERSON)
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonService personService;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PersonDefaultDto>> getAllPersonActivated() {
        log.info("PersonController.getAllPersonActivated");
        return new ResponseEntity<>(personMapper
                .mapListPersonEntityToPersonDefaultDto(personService.getAllEntityActivated()),HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PersonDefaultDto>> getAllPersonInactivated() {
        log.info("PersonController.getAllPersonInactivated");
        return new ResponseEntity<>(personMapper
                .mapListPersonEntityToPersonDefaultDto(personService.getAllEntityDeactivated()),HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonResponseByIdDto> getPersonById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("PersonController.getPersonById");
        log.info("PersonController.getPersonById.codigo: " + codigo);
        return personService.existsEntityById(codigo) ? new ResponseEntity<>(
                personMapper.mapPersonEntityToPersonResponseByIdDto(personService.getAllEntityById(codigo)),
                HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/existsDoiAndTd", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> existsPersonEntityByIdTypeDocumentAndDoi(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                                           @NotEmpty(message = MSG_EMPTY) Integer tipoDocumento, String numeroDocumento) {
        log.info("PersonController.existsPersonEntityByIdTypeDocumentAndDoi");
        log.info("PersonController.existsPersonEntityByIdTypeDocumentAndDoi.tipoDocumento: " + tipoDocumento);
        log.info("PersonController.existsPersonEntityByIdTypeDocumentAndDoi.numeroDocumento: " + numeroDocumento);
        return new ResponseEntity<>(personService.existsPersonEntityByIdTypeDocumentAndDoi(tipoDocumento, numeroDocumento) ? HttpStatus.ACCEPTED : HttpStatus.NO_CONTENT);
    }

}