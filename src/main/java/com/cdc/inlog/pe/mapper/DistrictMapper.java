package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.district.DistrictDefaultDto;
import com.cdc.inlog.pe.entity.DistrictEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    @Mapping(source = "provinceEntity.departmentEntity.codeDepartment", target = "provincia.departamento.codigoDepartamento")
    @Mapping(source = "provinceEntity.departmentEntity.department", target = "provincia.departamento.nombre")
    @Mapping(source = "provinceEntity.codeProvince", target = "provincia.codigoProvincia")
    @Mapping(source = "provinceEntity.province", target = "provincia.nombre")
    @Mapping(source = "codeDistrict", target = "codigoDistrito")
    @Mapping(source = "district", target = "nombre")
    DistrictDefaultDto mapDistrictEntityToDistrictDefaultDto(DistrictEntity districtEntity);

    List<DistrictDefaultDto> mapListDistrictEntityToDistrictDefaultDto(List<DistrictEntity> districtEntities);

}