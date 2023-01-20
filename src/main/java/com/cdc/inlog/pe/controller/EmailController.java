package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_EMAIL;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_EMPTY;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_PATCH_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_REGISTER;
import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;
import com.cdc.inlog.pe.dto.email.*;
import com.cdc.inlog.pe.mapper.EmailMapper;
import com.cdc.inlog.pe.service.EmailService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = API_EMAIL)
public class EmailController {

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private EmailService emailService;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<EmailDefaultDto>> getAllEmailActivated() {
        log.info("EmailController.getAllEmailActivated");
        return new ResponseEntity<>(emailMapper.mapListEmailEntityToEmailDefaultDto(
                emailService.getAllEntityActivated()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<EmailDefaultDto>> getAllEmailInactivated() {
        log.info("EmailController.getAllEmailInactivated");
        return new ResponseEntity<>(emailMapper.mapListEmailEntityToEmailDefaultDto(
                emailService.getAllEntityDeactivated()), HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmailResponseDto> registerEmail(@RequestBody EmailRequestDto emailRequestDto) {
        log.info("EmailController.registerEmail");
        return new ResponseEntity<>(emailMapper.mapEmailEntityToEmailResponseDto(
                emailService.registerEntity(
                        emailMapper.mapEmailRequestDtoToEmailEntity(emailRequestDto,
                                LocalDate.now(ZoneId.of(TIME_ZONE_PERU))))),
                HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getEmailById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("EmailController.getEmailById");
        log.info("EmailController.getEmailById.codigo: " + codigo);
        return emailService.existsEntityById(codigo) ? new ResponseEntity<>(
                emailMapper.mapEmailEntityToEmailResponseByIdDto(emailService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateEmailById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                      @NotEmpty(message = MSG_EMPTY) Integer codigo,
                                                  @RequestBody EmailUpdateDto emailUpdateDto) {
        log.info("EmailController.updateEmailById");
        log.info("EmailController.updateEmailById.codigo: " + codigo);
        Integer result = emailService.updateEntityById(
                emailMapper.mapEmailUpdateDtoToEmailEntity(emailUpdateDto, codigo));
        log.info("EmailController.updateEmailById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_ACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> activateEmailById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                        @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("EmailController.activateEmailById");
        log.info("EmailController.activateEmailById.codigo: " + codigo);
        Integer result = emailService.checkActiveById(codigo);
        log.info("EmailController.activateEmailById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_TWO) ?
                        emailService.activateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactivateEmailById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                          @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("EmailController.inactivateEmailById");
        log.info("EmailController.inactivateEmailById.codigo: " + codigo);
        Integer result = emailService.checkActiveById(codigo);
        log.info("EmailController.inactivateEmailById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        emailService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

}