package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.nationality.NationalityDefaultDto;
import com.cdc.inlog.pe.entity.NationalityEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NationalityMapper {

    @Mapping(source = "idNationality", target = "codigo")
    @Mapping(source = "iso", target = "abreviatura")
    @Mapping(source = "country", target = "nombre")
    NationalityDefaultDto mapNationalityEntityToNationalityDefaultDto(NationalityEntity nationalityEntity);

    List<NationalityDefaultDto> mapListNationalityEntityToNationalityDefaultDto(List<NationalityEntity> nationalityEntities);

}