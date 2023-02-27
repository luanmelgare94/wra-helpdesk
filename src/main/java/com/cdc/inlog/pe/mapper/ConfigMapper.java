package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.config.ConfigDefaultDto;
import com.cdc.inlog.pe.dto.config.ConfigResponseByIdDto;
import com.cdc.inlog.pe.dto.config.ConfigUpdateDto;
import com.cdc.inlog.pe.entity.ConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfigMapper {

    @Mapping(source = "idConfig", target = "codigo")
    @Mapping(source = "parameter", target = "parametro")
    @Mapping(source = "status", target = "estado")
    ConfigDefaultDto mapConfigEntityToConfigDefaultDto(ConfigEntity configEntity);

    List<ConfigDefaultDto> mapListConfigEntityToListConfigDefaultDto(List<ConfigEntity> configEntities);

    @Mapping(source = "idConfig", target = "codigo")
    @Mapping(source = "parameter", target = "parametro")
    @Mapping(source = "status", target = "estado")
    ConfigResponseByIdDto mapConfigEntityToConfigResponseByIdDto(ConfigEntity configEntity);

    @Mapping(source = "codigo", target = "idConfig")
    @Mapping(source = "configUpdateDto.parametro", target = "parameter")
    @Mapping(source = "configUpdateDto.estado", target = "status")
    ConfigEntity mapConfigUpdateDtoToConfigEntity(ConfigUpdateDto configUpdateDto, Integer codigo);

}