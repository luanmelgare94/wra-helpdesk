package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_MENU;
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
import com.cdc.inlog.pe.dto.menu.MenuDefaultDto;
import com.cdc.inlog.pe.dto.menu.MenuRequestDto;
import com.cdc.inlog.pe.dto.menu.MenuResponseDto;
import com.cdc.inlog.pe.dto.menu.MenuUpdateDto;
import com.cdc.inlog.pe.mapper.MenuMapper;
import com.cdc.inlog.pe.service.MenuService;
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
@RequestMapping(path = API_MENU)
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<MenuDefaultDto>> getAllMenuActivated() {
        log.info("MenuController.getAllMenuActivated");
        return new ResponseEntity<>(menuMapper.mapListMenuEntityToMenuDefaultDto(menuService.getAllEntityActivated()),
                HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<MenuDefaultDto>> getAllMenuInactivated() {
        log.info("MenuController.getAllMenuInactivated");
        return new ResponseEntity<>(menuMapper.mapListMenuEntityToMenuDefaultDto(menuService.getAllEntityDeactivated()),
                HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MenuResponseDto> registerMenu(@RequestBody MenuRequestDto menuRequestDto) {
        log.info("MenuController.registerMenu");
        return new ResponseEntity<>(menuMapper.mapMenuEntityToMenuResponseDto(
                menuService.registerEntity(
                        menuMapper.mapMenuRequestDtoToMenuEntity(menuRequestDto))), HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                              @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("MenuController.getMenuById");
        log.info("MenuController.getMenuById.codigo: " + codigo);
        return menuService.existsEntityById(codigo) ? new ResponseEntity<>(
                menuMapper.mapMenuEntityToMenuResponseByIdDto(menuService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                 @NotEmpty(message = MSG_EMPTY) Integer codigo,
                                                 @RequestBody MenuUpdateDto menuUpdateDto) {
        log.info("MenuController.updateMenuById");
        log.info("MenuController.updateMenuById.codigo: " + codigo);
        Integer result = menuService.updateEntityById(
                menuMapper.mapMenuUpdateDtoToMenuEntity(menuUpdateDto, codigo));
        log.info("MenuController.updateMenuById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_ACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> activateMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("MenuController.activateMenuById");
        log.info("MenuController.activateMenuById.codigo: " + codigo);
        Integer result = menuService.checkActiveById(codigo);
        log.info("MenuController.activateMenuById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_TWO) ?
                        menuService.activateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactivateMenuById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("MenuController.inactivateMenuById");
        log.info("MenuController.inactivateMenuById.codigo: " + codigo);
        Integer result = menuService.checkActiveById(codigo);
        log.info("MenuController.inactivateMenuById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        menuService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

}