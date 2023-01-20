package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.role.*;
import com.cdc.inlog.pe.entity.RoleEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "idRole", target = "codigo")
    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    RoleDefaultDto  mapRoleEntityToRoleDefaultDto(RoleEntity roleEntity);

    List<RoleDefaultDto> mapListRoleEntityToRoleDefaultDto(List<RoleEntity> roleEntities);

    @Mapping(source = "roleRequestDto.rol", target = "role")
    @Mapping(source = "roleRequestDto.descripcion", target = "description")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    RoleEntity mapRoleRequestDtoToRoleEntity(RoleRequestDto roleRequestDto, LocalDateTime now);

    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    RoleResponseDto mapRoleEntityToRoleResponseDto(RoleEntity roleEntity);

    @Mapping(source = "idRole", target = "codigo")
    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    RoleResponseByIdDto mapRoleEntityToRoleResponseByIdDto(RoleEntity roleEntity);

    @Mapping(source = "codigo", target = "idRole")
    @Mapping(source = "roleUpdateDto.rol", target = "role")
    @Mapping(source = "roleUpdateDto.descripcion", target = "description")
    RoleEntity mapRoleUpdateDtoToRoleEntity(RoleUpdateDto roleUpdateDto, Integer codigo);

}