package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.typecontract.TypeContractDefaultDto;
import com.cdc.inlog.pe.entity.TypeContractEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeContractMapper {

    @Mapping(source = "idTypeContract", target = "codigo")
    @Mapping(source = "typeContract", target = "nombre")
    TypeContractDefaultDto mapTypeContractEntityToTypeContractDefaultDto(TypeContractEntity typeContractEntity);

    List<TypeContractDefaultDto> mapListTypeContractEntityToTypeContractDefaultDto(List<TypeContractEntity> typeContractEntityList);

}