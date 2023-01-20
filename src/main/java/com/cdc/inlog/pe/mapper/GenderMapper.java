package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.gender.GenderDefaultDto;
import com.cdc.inlog.pe.entity.GenderEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenderMapper {

    @Mapping(source = "idGender", target = "codigo")
    @Mapping(source = "iso", target = "abreviatura")
    @Mapping(source = "gender", target = "nombre")
    GenderDefaultDto mapGenderEntityToGenderDefaultDto(GenderEntity genderEntity);

    List<GenderDefaultDto> mapListGenderEntityToGenderDefaultDto(List<GenderEntity> genderEntities);

}