package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.priority.PriorityDefaultDto;
import com.cdc.inlog.pe.dto.priority.PriorityResponseByIdDto;
import com.cdc.inlog.pe.entity.PriorityEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriorityMapper {

    @Mapping(source = "idPriority", target = "codigo")
    @Mapping(source = "priority", target = "nombre")
    PriorityDefaultDto mapPriorityEntityToPriorityDefaultDto(PriorityEntity priorityEntity);

    List<PriorityDefaultDto> mapListPriorityEntityToPriorityDefaultDto(List<PriorityEntity> priorityEntities);

    @Mapping(source = "idPriority", target = "codigo")
    @Mapping(source = "priority", target = "nombre")
    @Mapping(source = "codeColor", target = "codigoColor")
    PriorityResponseByIdDto mapPriorityEntityToPriorityResponseByIdDto(PriorityEntity priorityEntity);

}