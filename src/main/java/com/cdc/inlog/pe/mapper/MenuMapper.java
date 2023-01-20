package com.cdc.inlog.pe.mapper;

import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;

import com.cdc.inlog.pe.dto.menu.*;
import com.cdc.inlog.pe.entity.MenuEntity;
import com.cdc.inlog.pe.entity.RoleEntity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    MenuDefaultDto.MenuDefaultRolesDto mapRoleEntityToMenuDefaultRolesDto(RoleEntity roleEntity);

    List<MenuDefaultDto.MenuDefaultRolesDto> mapListRoleEntityToMenuDefaultRolesDto(List<RoleEntity> roleEntities);

    @Mapping(source = "menuEntity.idMenu", target = "codigo")
    @Mapping(source = "menuEntity.icon", target = "icono")
    @Mapping(source = "menuEntity.menu", target = "menu")
    @Mapping(source = "menuEntity.url", target = "link")
    @Mapping(source = "menuEntity.observation", target = "observacion")
    @Mapping(source = "menuDefaultRolesDtos", target = "roles")
    MenuDefaultDto mapMenuEntityToMenuDefaultDtoSimple(MenuEntity menuEntity,
                                                       List<MenuDefaultDto.MenuDefaultRolesDto> menuDefaultRolesDtos);

    @Named(value = "menuDefaultDto")
    default MenuDefaultDto mapMenuEntityToMenuDefaultDto(MenuEntity menuEntity) {
        return mapMenuEntityToMenuDefaultDtoSimple(menuEntity,
                mapListRoleEntityToMenuDefaultRolesDto(menuEntity.getRoles()));
    }

    @IterableMapping(qualifiedByName = "menuDefaultDto")
    List<MenuDefaultDto> mapListMenuEntityToMenuDefaultDto(List<MenuEntity> menuEntities);

    @Mapping(source = "menuRequestDto.icono", target = "icon")
    @Mapping(source = "menuRequestDto.menu", target = "menu")
    @Mapping(source = "menuRequestDto.link", target = "url")
    @Mapping(source = "menuRequestDto.observacion", target = "observation")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    MenuEntity mapMenuRequestDtoToMenuEntitySimple(MenuRequestDto menuRequestDto, LocalDateTime now);

    @Mapping(source = "dto.codigo", target = "idRole")
    RoleEntity mapMenuRequestRoleDtoToRoleEntity(MenuRequestDto.MenuRequestRoleDto dto);

    default MenuEntity mapMenuRequestDtoToMenuEntity(MenuRequestDto menuRequestDto) {
        MenuEntity menuEntity = mapMenuRequestDtoToMenuEntitySimple(menuRequestDto,
                LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU)));
        List<RoleEntity> roleEntityList = new ArrayList<>();
        menuRequestDto.getRoles().forEach(menuRequestRoleDto -> roleEntityList.add(
                mapMenuRequestRoleDtoToRoleEntity(menuRequestRoleDto)));
        menuEntity.setRoles(roleEntityList);
        return menuEntity;
    }

    @Mapping(source = "icon", target = "icono")
    @Mapping(source = "menu", target = "menu")
    @Mapping(source = "url", target = "link")
    MenuResponseDto mapMenuEntityToMenuResponseDtoSimple(MenuEntity menuEntity);

    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    MenuResponseDto.MenuResponseRoleDto mapRoleEntityToMenuResponseRoleDto(RoleEntity roleEntity);

    default MenuResponseDto mapMenuEntityToMenuResponseDto(MenuEntity menuEntity) {
        MenuResponseDto menuResponseDto = mapMenuEntityToMenuResponseDtoSimple(menuEntity);
        List<MenuResponseDto.MenuResponseRoleDto> menuResponseRoleDtos = new ArrayList<>();
        menuEntity.getRoles().forEach(roleEntity -> menuResponseRoleDtos.add(
                mapRoleEntityToMenuResponseRoleDto(roleEntity)));
        menuResponseDto.setRoles(menuResponseRoleDtos);
        return menuResponseDto;
    }

    @Mapping(source = "idMenu", target = "codigo")
    @Mapping(source = "icon", target = "icono")
    @Mapping(source = "menu", target = "menu")
    @Mapping(source = "url", target = "link")
    @Mapping(source = "observation", target = "observacion")
    MenuResponseByIdDto mapMenuEntityToMenuResponseByIdDtoSimple(MenuEntity menuEntity);

    @Mapping(source = "idRole", target = "codigo")
    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    MenuResponseByIdDto.MenuResponseByIdRol mapRoleEntityToMenuResponseByIdRol(RoleEntity roleEntity);

    default MenuResponseByIdDto mapMenuEntityToMenuResponseByIdDto(MenuEntity menuEntity) {
        MenuResponseByIdDto menuResponseByIdDto = mapMenuEntityToMenuResponseByIdDtoSimple(menuEntity);
        List<MenuResponseByIdDto.MenuResponseByIdRol> menuResponseByIdRolList = new ArrayList<>();
        menuEntity.getRoles().forEach(roleEntity -> menuResponseByIdRolList.add(
                mapRoleEntityToMenuResponseByIdRol(roleEntity)));
        menuResponseByIdDto.setRoles(menuResponseByIdRolList);
        return menuResponseByIdDto;
    }

    @Mapping(source = "codigo", target = "idMenu")
    @Mapping(source = "menuUpdateDto.icono", target = "icon")
    @Mapping(source = "menuUpdateDto.menu", target = "menu")
    @Mapping(source = "menuUpdateDto.link", target = "url")
    @Mapping(source = "menuUpdateDto.observacion", target = "observation")
    MenuEntity mapMenuUpdateDtoToMenuEntitySimple(MenuUpdateDto menuUpdateDto, Integer codigo);

    @Mapping(source = "menuUpdateRolDto.codigo", target = "idRole")
    RoleEntity mapMenuUpdateRolDtoToRoleEntity(MenuUpdateDto.MenuUpdateRolDto menuUpdateRolDto);

    default MenuEntity mapMenuUpdateDtoToMenuEntity(MenuUpdateDto menuUpdateDto, Integer codigo) {
        MenuEntity menuEntity = mapMenuUpdateDtoToMenuEntitySimple(menuUpdateDto, codigo);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        menuUpdateDto.getRoles().forEach(menuUpdateRolDto -> roleEntityList.add(
                mapMenuUpdateRolDtoToRoleEntity(menuUpdateRolDto)));
        menuEntity.setRoles(roleEntityList);
        return menuEntity;
    }

}