package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_ROLE;
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
import com.cdc.inlog.pe.dto.role.RoleDefaultDto;
import com.cdc.inlog.pe.dto.role.RoleRequestDto;
import com.cdc.inlog.pe.dto.role.RoleResponseDto;
import com.cdc.inlog.pe.dto.role.RoleUpdateDto;
import com.cdc.inlog.pe.mapper.RoleMapper;
import com.cdc.inlog.pe.service.RoleService;
import java.time.LocalDateTime;
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
@RequestMapping(path = API_ROLE)
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RoleDefaultDto>> getAllRoleActivated() {
        log.info("RoleController.getAllRoleActivated");
        return new ResponseEntity<>(roleMapper.mapListRoleEntityToRoleDefaultDto(roleService.getAllEntityActivated()),
                HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RoleDefaultDto>> getAllRoleInactivated() {
        log.info("RoleController.getAllRoleInactivated");
        return new ResponseEntity<>(roleMapper.mapListRoleEntityToRoleDefaultDto(roleService.getAllEntityDeactivated()),
                HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleResponseDto> registerRole(@RequestBody RoleRequestDto roleRequestDto) {
        log.info("RoleController.registerRole");
        return new ResponseEntity<>(roleMapper.mapRoleEntityToRoleResponseDto(
                roleService.registerEntity(
                        roleMapper.mapRoleRequestDtoToRoleEntity(roleRequestDto, LocalDateTime.now(
                                ZoneId.of(TIME_ZONE_PERU))))), HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                  @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("RoleController.getMenuById");
        log.info("RoleController.getMenuById.codigo: " + codigo);
        return roleService.existsEntityById(codigo) ? new ResponseEntity<>(
                roleMapper.mapRoleEntityToRoleResponseByIdDto(roleService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                 @NotEmpty(message = MSG_EMPTY) Integer codigo,
                                                 @RequestBody RoleUpdateDto roleUpdateDto) {
        log.info("RoleController.updateMenuById");
        log.info("RoleController.updateMenuById.codigo: " + codigo);
        Integer result = roleService.updateEntityById(
                roleMapper.mapRoleUpdateDtoToRoleEntity(roleUpdateDto, codigo));
        log.info("RoleController.updateMenuById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_ACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> activateMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("RoleController.activateMenuById");
        log.info("RoleController.activateMenuById.codigo: " + codigo);
        Integer result = roleService.checkActiveById(codigo);
        log.info("RoleController.activateMenuById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_TWO) ?
                        roleService.activateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactivateMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                     @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("RoleController.inactivateMenuById");
        log.info("RoleController.inactivateMenuById.codigo: " + codigo);
        Integer result = roleService.checkActiveById(codigo);
        log.info("RoleController.inactivateMenuById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        roleService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

}