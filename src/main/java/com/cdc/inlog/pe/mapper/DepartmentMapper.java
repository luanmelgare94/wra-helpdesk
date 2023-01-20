package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.department.DepartmentDefaultDto;
import com.cdc.inlog.pe.entity.DepartmentEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(source = "codeDepartment", target = "codigoDepartamento")
    @Mapping(source = "department", target = "nombre")
    DepartmentDefaultDto mapDepartmentEntityToDepartmentDefaultDto(DepartmentEntity departmentEntity);

    List<DepartmentDefaultDto> mapListDepartmentEntityToDepartmentDefaultDto(List<DepartmentEntity> departmentEntities);

}