package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.operator.OperatorDefaultDto;
import com.cdc.inlog.pe.entity.OperatorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperatorMapper {

    @Mapping(source = "idOperator", target = "codigo")
    @Mapping(source = "operator", target = "nombre")
    OperatorDefaultDto mapOperatorEntityToOperatorDefaultDto(OperatorEntity operatorEntity);

    List<OperatorDefaultDto> mapListOperatorEntityToOperatorDefaultDto(List<OperatorEntity> operatorEntities);

}