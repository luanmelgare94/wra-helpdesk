package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_PHONE;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_EMPTY;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED_BY_ID_PERSON;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED_BY_ID_PERSON;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_PATCH_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_REGISTER;
import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;
import com.cdc.inlog.pe.dto.phone.*;
import com.cdc.inlog.pe.mapper.PhoneMapper;
import com.cdc.inlog.pe.service.PhoneService;
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
@RequestMapping(path = API_PHONE)
public class PhoneController {

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private PhoneService phoneService;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PhoneDefaultDto>> getAllPhoneActivated() {
        log.info("PhoneController.getAllPhoneActivated");
        return new ResponseEntity<>(phoneMapper.mapListPhoneEntityToPhoneDefaultDto(phoneService.getAllEntityActivated()),
                HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PhoneDefaultDto>> getAllPhoneInactivated() {
        log.info("PhoneController.getAllPhoneInactivated");
        return new ResponseEntity<>(phoneMapper.mapListPhoneEntityToPhoneDefaultDto(phoneService.getAllEntityDeactivated()),
                HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_ACTIVATED_BY_ID_PERSON, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PhoneDefaultDto>> getAllPhoneActivatedByIdPerson(@RequestParam
                                                                                    @Min(value = 1, message = MSG_POSITIVE)
                                                                                    @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("EmailController.getAllPhoneActivatedByIdPerson");
        log.info("EmailController.getAllPhoneActivatedByIdPerson.idPerson: " + codigo);
        return new ResponseEntity<>(phoneMapper.mapListPhoneEntityToPhoneDefaultDto(
                phoneService.getAllPhoneEntityActivatedByIdPerson(codigo)), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED_BY_ID_PERSON, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PhoneDefaultDto>> getAllPhoneDeactivatedByIdPerson(@RequestParam
                                                                                @Min(value = 1, message = MSG_POSITIVE)
                                                                                @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("EmailController.getAllPhoneDeactivatedByIdPerson");
        log.info("EmailController.getAllPhoneDeactivatedByIdPerson.idPerson: " + codigo);
        return new ResponseEntity<>(phoneMapper.mapListPhoneEntityToPhoneDefaultDto(
                phoneService.getAllPhoneEntityDeactivatedByIdPerson(codigo)), HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PhoneResponseDto> registerPhone(@RequestBody PhoneRequestDto phoneRequestDto) {
        log.info("PhoneController.registerPhone");
        return new ResponseEntity<>(phoneMapper.mapPhoneEntityToPhoneResponseDto(
                phoneService.registerEntity(
                        phoneMapper.mapPhoneRequestDtoToPhoneEntity(phoneRequestDto,
                                LocalDate.now(ZoneId.of(TIME_ZONE_PERU))))),
                HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PhoneResponseByIdDto> getPhoneById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("PhoneController.getPhoneById");
        log.info("PhoneController.getPhoneById.codigo: " + codigo);
        return phoneService.existsEntityById(codigo) ? new ResponseEntity<>(
                phoneMapper.mapPhoneEntityToPhoneResponseByIdDto(phoneService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updatePhoneById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                  @NotEmpty(message = MSG_EMPTY) Integer codigo,
                                                  @RequestBody PhoneUpdateDto phoneUpdateDto) {
        log.info("PhoneController.updatePhoneById");
        log.info("PhoneController.updatePhoneById.codigo: " + codigo);
        Integer result = phoneService.updateEntityById(
                phoneMapper.mapPhoneUpdateDtoToPhoneEntity(phoneUpdateDto, codigo));
        log.info("PhoneController.updatePhoneById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_ACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> activatePhoneById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                    @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("PhoneController.activatePhoneById");
        log.info("PhoneController.activatePhoneById.codigo: " + codigo);
        Integer result = phoneService.checkActiveById(codigo);
        log.info("PhoneController.activatePhoneById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_TWO) ?
                        phoneService.activateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactivatePhoneById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                    @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("PhoneController.inactivatePhoneById");
        log.info("PhoneController.inactivatePhoneById.codigo: " + codigo);
        Integer result = phoneService.checkActiveById(codigo);
        log.info("PhoneController.inactivatePhoneById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        phoneService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

}