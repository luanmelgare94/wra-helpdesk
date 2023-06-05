package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_USERNAME;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_EMPTY;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_USERNAME;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_PASSWORD_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_REGISTER;

import com.cdc.inlog.pe.dto.page.PagedResponseDto;
import com.cdc.inlog.pe.dto.username.UsernameDefaultDto;
import com.cdc.inlog.pe.dto.username.UsernameRequestDto;
import com.cdc.inlog.pe.dto.username.UsernameUpdateDto;
import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.mapper.PageMapper;
import com.cdc.inlog.pe.mapper.UsernameMapper;
import com.cdc.inlog.pe.service.UsernameService;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = API_USERNAME)
public class UsernameController {

    @Autowired
    private UsernameMapper usernameMapper;

    @Autowired
    private UsernameService usernameService;

    @Autowired
    private PageMapper pageMapper;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedResponseDto<UsernameDefaultDto>> getAllUsernameActivated(Pageable pageable) {
        log.info("UsernameController.getAllUsernameActivated");
        log.info("UsernameController.getAllUsernameActivated.pageNumber: " + pageable.getPageNumber());
        log.info("UsernameController.getAllUsernameActivated.pageSize: " + pageable.getPageSize());
        Page<UsernameEntity> usernameEntityPage = usernameService.getAllEntityActivated(pageable);
        return new ResponseEntity<>(new PagedResponseDto<UsernameDefaultDto>(
                usernameMapper.mapListUsernameEntityToUsernameDefaultDto(usernameEntityPage.getContent()),
                pageMapper.mapPageableToPageableDto(usernameEntityPage.getPageable()),
                usernameEntityPage.isLast(), usernameEntityPage.getTotalPages(),
                usernameEntityPage.getTotalElements(), usernameEntityPage.getSize(), usernameEntityPage.getNumber(),
                pageMapper.mapSortToSortDto(usernameEntityPage.getPageable()), usernameEntityPage.isFirst(),
                usernameEntityPage.getNumberOfElements(), usernameEntityPage.isEmpty()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedResponseDto<UsernameDefaultDto>> getAllUsernameInactivated(Pageable pageable) {
        log.info("UsernameController.getAllUsernameInactivated");
        log.info("UsernameController.getAllUsernameInactivated.pageNumber: " + pageable.getPageNumber());
        log.info("UsernameController.getAllUsernameInactivated.pageSize: " + pageable.getPageSize());
        Page<UsernameEntity> usernameEntityPage = usernameService.getAllEntityDeactivated(pageable);
        return new ResponseEntity<>(new PagedResponseDto<UsernameDefaultDto>(
                usernameMapper.mapListUsernameEntityToUsernameDefaultDto(usernameEntityPage.getContent()),
                pageMapper.mapPageableToPageableDto(usernameEntityPage.getPageable()),
                usernameEntityPage.isLast(), usernameEntityPage.getTotalPages(),
                usernameEntityPage.getTotalElements(), usernameEntityPage.getSize(), usernameEntityPage.getNumber(),
                pageMapper.mapSortToSortDto(usernameEntityPage.getPageable()), usernameEntityPage.isFirst(),
                usernameEntityPage.getNumberOfElements(), usernameEntityPage.isEmpty()), HttpStatus.OK);
    }

    @GetMapping(path = "/temporal", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UsernameDefaultDto>> getAllTemp() {
        return new ResponseEntity<>(usernameMapper.mapListUsernameEntityToUsernameDefaultDto(usernameService.getAllEntityActivated()), HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> registerUsername(@RequestBody UsernameRequestDto usernameRequestDto) {
        log.info("UsernameController.registerUsername");
        return new ResponseEntity<>(usernameMapper.mapUsernameEntityToUsernameResponseDto(
                usernameService.registerEntity(
                        usernameMapper.mapUsernameRequestDtoToUsernameEntity(usernameRequestDto))), HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getUsernameById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                  @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("UsernameController.getUsernameById");
        log.info("UsernameController.getUsernameById.codigo: " + codigo);
        return usernameService.existsEntityById(codigo) ? new ResponseEntity<>(
                usernameMapper.mapUsernameEntityToUsernameResponseByIdDto(usernameService.getAllEntityById(codigo)),
                HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = SUB_API_GET_BY_USERNAME, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getUsernameByUsername(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                  @NotEmpty(message = MSG_EMPTY) String usuario) {
        log.info("UsernameController.getUsernameByUsername");
        log.info("UsernameController.getUsernameByUsername.usuario: " + usuario);
        return usernameService.existsEntityByUsername(usuario) ? new ResponseEntity<>(
                usernameMapper.mapUsernameEntityToUsernameResponseByIdDto(usernameService.getEntityByUsername(usuario)),
                HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/getid", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UsernameEntity> getById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                  @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        return new ResponseEntity<>(usernameService.getAllEntityById(codigo), HttpStatus.OK);
    }

    @PatchMapping(path = SUB_API_PASSWORD_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> changePassword(@RequestBody UsernameUpdateDto usernameUpdateDto) {
        log.info("UsernameController.changePassword");
        log.info("UsernameController.changePassword.usuario: " + usernameUpdateDto.getUsuario());
        log.info("UsernameController.changePassword.contraseña: " + usernameUpdateDto.getContrasena());
        Integer result = usernameService.updateUsernameEntityPasswdByIdUsername(usernameUpdateDto.getUsuario(),
                usernameUpdateDto.getContrasena());
        log.info("UsernameController.changePassword.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_ACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> activateUsernameById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                           @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("UsernameController.activateUsernameById");
        log.info("UsernameController.activateUsernameById.codigo: " + codigo);
        Integer result = usernameService.checkActiveById(codigo);
        log.info("UsernameController.activateUsernameById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_TWO) ?
                        usernameService.activateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactivateUsernameById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                       @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("UsernameController.inactivateUsernameById");
        log.info("UsernameController.inactivateUsernameById.codigo: " + codigo);
        Integer result = usernameService.checkActiveById(codigo);
        log.info("UsernameController.inactivateUsernameById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        usernameService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    /*@GetMapping(path = "/paged")
    public ResponseEntity<Object> getAllPage(Pageable pageable) {
        return new ResponseEntity<>(usernameService.getAllEntity(pageable), HttpStatus.OK);
    }*/

    /*@GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UsernameDefaultDto>> getAllUsernameActivated() {
        log.info("UsernameController.getAllUsernameActivated");
        return new ResponseEntity<>(usernameMapper.mapListUsernameEntityToUsernameDefaultDto(
                usernameService.getAllEntityActivated()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UsernameDefaultDto>> getAllUsernameInactivated() {
        log.info("UsernameController.getAllUsernameInactivated");
        return new ResponseEntity<>(usernameMapper.mapListUsernameEntityToUsernameDefaultDto(
                usernameService.getAllEntityDeactivated()), HttpStatus.OK);
    }*/

}