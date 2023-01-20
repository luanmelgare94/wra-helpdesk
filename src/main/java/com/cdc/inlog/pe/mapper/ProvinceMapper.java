package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.province.ProvinceDefaultDto;
import com.cdc.inlog.pe.entity.ProvinceEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {

    @Mapping(source = "departmentEntity.codeDepartment", target = "departamento.codigoDepartamento")
    @Mapping(source = "departmentEntity.department", target = "departamento.nombre")
    @Mapping(source = "codeProvince", target = "codigoProvincia")
    @Mapping(source = "province", target = "nombre")
    ProvinceDefaultDto mapProvinceEntityToProvinceDefaultDto(ProvinceEntity provinceEntity);

    List<ProvinceDefaultDto> mapListProvinceEntityToProvinceDefaultDto(List<ProvinceEntity> provinceEntities);

}