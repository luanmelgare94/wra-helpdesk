package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.typephone.TypePhoneDefaultDto;
import com.cdc.inlog.pe.entity.TypePhoneEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypePhoneMapper {

    @Mapping(source = "idTypePhone", target = "codigo")
    @Mapping(source = "typePhone", target = "nombre")
    TypePhoneDefaultDto mapTypePhoneEntityToTypePhoneDefaultDto(TypePhoneEntity typePhoneEntity);

    List<TypePhoneDefaultDto> mapListTypePhoneEntityToTypePhoneDefaultDto(List<TypePhoneEntity> typePhoneEntities);

}