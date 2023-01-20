package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_CONFIG;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_PATCH_BY_ID;
import com.cdc.inlog.pe.dto.config.ConfigUpdateDto;
import com.cdc.inlog.pe.mapper.ConfigMapper;
import com.cdc.inlog.pe.service.ConfigService;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(path = API_CONFIG)
public class ConfigController {

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private ConfigService configService;

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getConfigById(@RequestParam @Min(value = 1, message = MSG_POSITIVE) Integer codigo) {
        log.info("ConfigController.getConfigById");
        log.info("ConfigController.getConfigById.codigo: " + codigo);
        return configService.existsEntityById(codigo) ? new ResponseEntity<>(
                configMapper.mapConfigEntityToConfigResponseByIdDto(configService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateConfigById(@RequestParam @Min(value = 1, message = MSG_POSITIVE) Integer codigo,
                                                  @RequestBody ConfigUpdateDto configUpdateDto) {
        log.info("ConfigController.updateConfigById");
        log.info("ConfigController.updateConfigById.codigo: " + codigo);
        Integer result = configService.updateEntityById(
                configMapper.mapConfigUpdateDtoToConfigEntity(configUpdateDto, codigo));
        log.info("ConfigController.updateConfigById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

}