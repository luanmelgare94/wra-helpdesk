package com.cdc.inlog.pe.service;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cdc.inlog.pe.entity.ConfigEntity;
import com.cdc.inlog.pe.repository.ConfigRepository;
import com.cdc.inlog.pe.service.impl.ConfigServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class TestConfigServiceImpl {

    @InjectMocks
    private ConfigServiceImpl configService;

    @Mock
    private ConfigRepository configRepository;

    @Test
    public void testGetAllEntity() {
        Iterable<ConfigEntity> configEntityIterable = configService.getAllEntity();
        assertThat(configEntityIterable).isEmpty();
    }

    @Test
    public void testGetAllEntityActivated() {
        Iterable<ConfigEntity> configEntityIterable = configService.getAllEntityActivated();
        assertThat(configEntityIterable).isEmpty();
    }

    @Test
    public void testGetAllEntityDeactivated() {
        Iterable<ConfigEntity> configEntityIterable = configService.getAllEntityDeactivated();
        assertThat(configEntityIterable).isEmpty();
    }

    @Test
    public void testGetAllEntityByIdSuccessfully() {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        configEntity.setStatus(NUMBER_ONE);
        configEntity.setParameter("Parameter");
        configEntity.setDateUpdate(LocalDateTime.now());
        configEntity.setDateRegister(LocalDateTime.now());
        when(configRepository.findById(anyInt())).thenReturn(Optional.of(configEntity));
        assertThat(configService.getAllEntityById(NUMBER_ONE)).isEqualTo(configEntity);
    }

    @Test
    public void testRegisterEntity() {
        ConfigEntity configEntity = configService.registerEntity(new ConfigEntity());
        assertThat(configEntity).isNull();
    }

    @Test
    public void testExistsEntityById() {
        when(configRepository.existsById(NUMBER_ONE)).thenReturn(Boolean.TRUE);
        assertThat(configService.existsEntityById(NUMBER_ONE)).isTrue();
    }

    @Test
    public void testUpdateEntityByIdWhenExistsId() {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        configEntity.setStatus(NUMBER_ONE);
        configEntity.setParameter("Parameter");
        configEntity.setDateUpdate(LocalDateTime.now());
        configEntity.setDateRegister(LocalDateTime.now());
        when(configRepository.existsById(anyInt())).thenReturn(Boolean.TRUE);
        //when(configRepository.updateConfigEntityByIdConfig(configEntity.getParameter(), configEntity.getStatus(), LocalDateTime.now(), configEntity.getIdConfig())).thenReturn(NUMBER_ZERO);
        assertThat(configService.updateEntityById(configEntity)).isEqualTo(NUMBER_ZERO);
    }

    @Test
    public void testUpdateEntityByIdWhenNotExistsId() {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setIdConfig(NUMBER_ONE);
        configEntity.setStatus(NUMBER_ONE);
        configEntity.setParameter("Parameter");
        configEntity.setDateUpdate(LocalDateTime.now());
        configEntity.setDateRegister(LocalDateTime.now());
        when(configRepository.existsById(anyInt())).thenReturn(Boolean.FALSE);
        assertThat(configService.updateEntityById(configEntity)).isEqualTo(NUMBER_TWO);
    }

    @Test
    public void testCheckActiveById() {
        Integer result = configService.checkActiveById(NUMBER_ONE);
        assertThat(result).isNull();
    }

    @Test
    public void testActivateEntityById() {
        boolean result = configService.activateEntityById(NUMBER_ONE);
        assertThat(result).isFalse();
    }

    @Test
    public void testDeactivateEntityById() {
        boolean result = configService.deactivateEntityById(NUMBER_ONE);
        assertThat(result).isFalse();
    }

}