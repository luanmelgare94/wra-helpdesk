package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.typeemail.TypeEmailDefaultDto;
import com.cdc.inlog.pe.entity.TypeEmailEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeEmailMapper {

    @Mapping(source = "idTypeEmail", target = "codigo")
    @Mapping(source = "typeEmail", target = "nombre")
    TypeEmailDefaultDto mapTypeEmailEntityToTypeEmailDefaultDto(TypeEmailEntity typeEmailEntity);

    List<TypeEmailDefaultDto> mapListTypeEmailEntityToTypeEmailDefaultDto(List<TypeEmailEntity> typeEmailEntityList);

}