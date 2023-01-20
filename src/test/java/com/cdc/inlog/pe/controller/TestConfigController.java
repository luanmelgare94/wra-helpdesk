package com.cdc.inlog.pe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.when;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import com.cdc.inlog.pe.dto.config.ConfigResponseByIdDto;
import com.cdc.inlog.pe.dto.config.ConfigUpdateDto;
import com.cdc.inlog.pe.entity.ConfigEntity;
import com.cdc.inlog.pe.mapper.ConfigMapperImpl;
import com.cdc.inlog.pe.service.ConfigService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class TestConfigController {

    @InjectMocks
    private ConfigController configController;

    @Mock
    private ConfigService configService;

    @Mock
    private ConfigMapperImpl configMapper;

    @Test
    public void testGetConfigByIdWhenIsSuccessfully() {

        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        configEntity.setParameter("parameter");
        configEntity.setStatus(NUMBER_ONE);

        when(configService.getAllEntityById(NUMBER_ONE)).thenReturn(configEntity);

        ConfigResponseByIdDto configResponseByIdDto = new ConfigResponseByIdDto();
        configResponseByIdDto.setCodigo(NUMBER_ONE);
        log.info("configUpdateDto.codigo: " + configResponseByIdDto.getCodigo());
        configResponseByIdDto.setParametro("parameter");
        log.info("configUpdateDto.parametro: " + configResponseByIdDto.getParametro());
        configResponseByIdDto.setEstado(NUMBER_ONE);
        log.info("configUpdateDto.estado: " + configResponseByIdDto.getEstado());

        when(configMapper.mapConfigEntityToConfigResponseByIdDto(configEntity)).thenReturn(configResponseByIdDto);

        when(configService.existsEntityById(refEq(NUMBER_ONE))).thenReturn(Boolean.TRUE);
        ResponseEntity<Object> httpResponse = configController.getConfigById(NUMBER_ONE);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetConfigByIdWhenIsNotContent() {
        when(configService.existsEntityById(refEq(NUMBER_ONE))).thenReturn(Boolean.FALSE);
        ResponseEntity<Object> httpResponse = configController.getConfigById(NUMBER_ONE);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testUpdateConfigByIdWhenIsSuccessfully() {
        ConfigUpdateDto configUpdateDto = new ConfigUpdateDto();
        configUpdateDto.setEstado(NUMBER_ONE);
        log.info("configUpdateDto.estado: " + configUpdateDto.getEstado());
        configUpdateDto.setParametro("parameter");
        log.info("configUpdateDto.parametro: " + configUpdateDto.getParametro());
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        log.info("configEntity.idConfig: " + configEntity.getIdConfig());
        configEntity.setParameter("parameter");
        log.info("configEntity.parameter: " + configEntity.getParameter());
        configEntity.setStatus(NUMBER_ONE);
        log.info("configEntity.status: " + configEntity.getStatus());
        configEntity.setDateRegister(LocalDateTime.now());
        log.info("configEntity.dateRegister: " + configEntity.getDateRegister());
        configEntity.setDateUpdate(LocalDateTime.now());
        log.info("configEntity.dateUpdate: " + configEntity.getDateUpdate());
        when(configMapper.mapConfigUpdateDtoToConfigEntity(configUpdateDto, NUMBER_ONE)).thenReturn(configEntity);
        when(configService.updateEntityById(refEq(configEntity))).thenReturn(NUMBER_ONE);
        ResponseEntity<Object> httpResponse = configController.updateConfigById(NUMBER_ONE, configUpdateDto);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void testUpdateConfigByIdWhenIsNoContent() {
        ConfigUpdateDto configUpdateDto = new ConfigUpdateDto();
        configUpdateDto.setEstado(NUMBER_ONE);
        configUpdateDto.setParametro("parameter");
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        configEntity.setParameter("parameter");
        configEntity.setStatus(NUMBER_ONE);
        configEntity.setDateRegister(LocalDateTime.now());
        configEntity.setDateUpdate(LocalDateTime.now());
        when(configMapper.mapConfigUpdateDtoToConfigEntity(configUpdateDto, NUMBER_ONE)).thenReturn(configEntity);
        when(configService.updateEntityById(refEq(configEntity))).thenReturn(NUMBER_TWO);
        ResponseEntity<Object> httpResponse = configController.updateConfigById(NUMBER_ONE, configUpdateDto);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testUpdateConfigByIdWhenIsResetContent() {
        ConfigUpdateDto configUpdateDto = new ConfigUpdateDto();
        configUpdateDto.setEstado(NUMBER_ONE);
        configUpdateDto.setParametro("parameter");
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        configEntity.setParameter("parameter");
        configEntity.setStatus(NUMBER_ONE);
        configEntity.setDateRegister(LocalDateTime.now());
        configEntity.setDateUpdate(LocalDateTime.now());
        when(configMapper.mapConfigUpdateDtoToConfigEntity(configUpdateDto, NUMBER_ONE)).thenReturn(configEntity);
        when(configService.updateEntityById(refEq(configEntity))).thenReturn(NUMBER_ZERO);
        ResponseEntity<Object> httpResponse = configController.updateConfigById(NUMBER_ONE, configUpdateDto);
        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.RESET_CONTENT);
    }

}