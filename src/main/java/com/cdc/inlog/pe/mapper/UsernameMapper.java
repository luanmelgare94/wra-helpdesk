package com.cdc.inlog.pe.mapper;

import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;

import com.cdc.inlog.pe.dto.username.UsernameDefaultDto;
import com.cdc.inlog.pe.dto.username.UsernameRequestDto;
import com.cdc.inlog.pe.dto.username.UsernameResponseByIdDto;
import com.cdc.inlog.pe.dto.username.UsernameResponseDto;
import com.cdc.inlog.pe.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UsernameMapper {

    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    UsernameDefaultDto.UsernameDefaultRoleDto mapRoleEntityToUsernameDefaultRoleDto(RoleEntity roleEntity);

    @Mapping(source = "usernameEntity.idUsername", target = "codigo")
    @Mapping(expression = "java(personEntity.getFullName())", target = "persona.nombrePersona")
    @Mapping(source = "usernameEntity.personEntity.nationalityEntity.country", target = "persona.nacionalidad")
    @Mapping(source = "usernameEntity.personEntity.genderEntity.gender", target = "persona.sexo")
    @Mapping(source = "usernameEntity.personEntity.typeDocumentEntity.typeDocument", target = "persona.tipoDocumento")
    @Mapping(source = "usernameEntity.personEntity.doi", target = "persona.numeroDocumento")
    @Mapping(source = "usernameEntity.username", target = "usuario")
    @Mapping(source = "usernameEntity.typeContractEntity.typeContract", target = "tipoContratacion")
    @Mapping(source = "list", target = "roles")
    UsernameDefaultDto mapUsernameEntityToUsernameDefaultDtoSimple(UsernameEntity usernameEntity,
                                                               List<UsernameDefaultDto.UsernameDefaultRoleDto> list);
    @Named(value = "usernameDefaultDto")
    default UsernameDefaultDto mapUsernameEntityToUsernameDefaultDto(UsernameEntity usernameEntity) {
        List<UsernameDefaultDto.UsernameDefaultRoleDto> usernameDefaultRoleDtos = new ArrayList<>();
        usernameEntity.getRoles()
                .forEach(roleEntity -> usernameDefaultRoleDtos.add(mapRoleEntityToUsernameDefaultRoleDto(roleEntity)));
        return mapUsernameEntityToUsernameDefaultDtoSimple(usernameEntity, usernameDefaultRoleDtos);
    }

    @IterableMapping(qualifiedByName = "usernameDefaultDto")
    List<UsernameDefaultDto> mapListUsernameEntityToUsernameDefaultDto(List<UsernameEntity> usernameEntities);

    @Mapping(source = "dto.tipoCorreo.codigo", target = "typeEmailEntity.idTypeEmail")
    @Mapping(source = "dto.correo", target = "email")
    @Mapping(source = "dto.usuarioRegistro", target = "userRegistration")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    @Mapping(source = "personEntity", target = "personEntity")
    EmailEntity mapUsernameRequestEmailDtoToEmailEntity(UsernameRequestDto.UsernameRequestEmailDto dto, LocalDate now,
                                                        PersonEntity personEntity);

    @Mapping(source = "dto.tipoTelefono.codigo", target = "typePhoneEntity.idTypePhone")
    @Mapping(source = "dto.operadorTelefonico.codigo", target = "operatorEntity.idOperator")
    @Mapping(source = "dto.telefono", target = "phone")
    @Mapping(source = "dto.usuarioRegistro", target = "userRegistration")
    @Mapping(source = "dto.observacion", target = "observation")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    @Mapping(source = "personEntity", target = "personEntity")
    PhoneEntity mapUsernameRequestPhoneDtoToPhoneEntity(UsernameRequestDto.UsernameRequestPhoneDto dto, LocalDate now,
                                                        PersonEntity personEntity);

    @Mapping(source = "dto.codigo", target = "idRole")
    RoleEntity mapUsernameRequestRoleDtoToRoleEntity(UsernameRequestDto.UsernameRequestRoleDto dto);

    @Mapping(source = "usernameRequestDto.persona.nombre", target = "personEntity.firstName")
    @Mapping(source = "usernameRequestDto.persona.apellidoPaterno", target = "personEntity.lastName")
    @Mapping(source = "usernameRequestDto.persona.apellidoMaterno", target = "personEntity.secondLastName")
    @Mapping(source = "usernameRequestDto.persona.nacionalidad.codigo", target = "personEntity.nationalityEntity.idNationality")
    @Mapping(source = "usernameRequestDto.persona.genero.codigo", target = "personEntity.genderEntity.idGender")
    @Mapping(source = "usernameRequestDto.persona.tipoDocumento.codigo", target = "personEntity.typeDocumentEntity.idTypeDocument")
    @Mapping(source = "usernameRequestDto.persona.numeroDocumento", target = "personEntity.doi")
    @Mapping(source = "usernameRequestDto.persona.onomastico", target = "personEntity.birthday")
    @Mapping(source = "usernameRequestDto.persona.usuarioRegistro", target = "personEntity.userRegistration")
    @Mapping(source = "usernameRequestDto.tipoContratacion.codigo", target = "typeContractEntity.idTypeContract")
    @Mapping(constant = "true", target = "personEntity.active")
    @Mapping(source = "now", target = "personEntity.dateRegister")
    @Mapping(source = "usernameRequestDto.nombreUsuario", target = "username")
    @Mapping(source = "usernameRequestDto.passwd", target = "passwd")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    UsernameEntity mapUsernameRequestDtoToUsernameEntitySimple(UsernameRequestDto usernameRequestDto, LocalDateTime now);

    default UsernameEntity mapUsernameRequestDtoToUsernameEntity(UsernameRequestDto usernameRequestDto) {
        UsernameEntity usernameEntity = mapUsernameRequestDtoToUsernameEntitySimple(usernameRequestDto,
                LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU)));
        List<EmailEntity> emailEntityList = new ArrayList<>();
        usernameRequestDto.getPersona().getCorreos().forEach(correo ->
                emailEntityList.add(mapUsernameRequestEmailDtoToEmailEntity(correo, LocalDate.now(ZoneId.of(TIME_ZONE_PERU)),
                        usernameEntity.getPersonEntity())));
        usernameEntity.getPersonEntity().setEmailEntityList(emailEntityList);
        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        usernameRequestDto.getPersona().getTelefonos().forEach(telefono ->
                phoneEntityList.add(mapUsernameRequestPhoneDtoToPhoneEntity(telefono, LocalDate.now(ZoneId.of(TIME_ZONE_PERU)),
                        usernameEntity.getPersonEntity())));
        usernameEntity.getPersonEntity().setPhoneEntityList(phoneEntityList);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        usernameRequestDto.getRoles().forEach(rol ->
                roleEntityList.add(mapUsernameRequestRoleDtoToRoleEntity(rol)));
        usernameEntity.setRoles(roleEntityList);
        return usernameEntity;
    }

    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    UsernameResponseDto.UsernameResponseRolesDto mapRoleEntityToUsernameResponseRolesDto(RoleEntity roleEntity);

    @Mapping(expression = "java(usernameEntity.getPersonEntity().getFullName())", target = "nombrePersona")
    @Mapping(source = "usernameEntity.typeContractEntity.typeContract", target = "tipoContratacion")
    @Mapping(source = "usernameEntity.username", target = "usuario")
    UsernameResponseDto mapUsernameEntityToUsernameResponseDtoSimple(UsernameEntity usernameEntity);

    default UsernameResponseDto mapUsernameEntityToUsernameResponseDto(UsernameEntity usernameEntity) {
        List<UsernameResponseDto.UsernameResponseRolesDto> usernameResponseRolesDtos = new ArrayList<>();
        UsernameResponseDto usernameResponseDto = mapUsernameEntityToUsernameResponseDtoSimple(usernameEntity);
        usernameEntity.getRoles()
                .forEach(roleEntity -> usernameResponseRolesDtos.add(
                        mapRoleEntityToUsernameResponseRolesDto(roleEntity)));
        usernameResponseDto.setRoles(usernameResponseRolesDtos);
        return usernameResponseDto;
    }

    @Mapping(source = "idEmail", target = "codigo")
    @Mapping(source = "typeEmailEntity.idTypeEmail", target = "tipoCorreo.codigo")
    @Mapping(source = "typeEmailEntity.typeEmail", target = "tipoCorreo.tipoCorreo")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "userRegistration", target = "usuarioRegistro")
    UsernameResponseByIdDto.UsernameResponseByIdEmailDto mapEmailEntityToUsernameResponseByIdEmailDto(
            EmailEntity emailEntity);

    @Mapping(source = "idPhone", target = "codigo")
    @Mapping(source = "typePhoneEntity.idTypePhone", target = "tipoTelefono.codigo")
    @Mapping(source = "typePhoneEntity.typePhone", target = "tipoTelefono.tipoTelefono")
    @Mapping(source = "operatorEntity.idOperator", target = "operadorTelefonico.codigo")
    @Mapping(source = "operatorEntity.operator", target = "operadorTelefonico.operador")
    @Mapping(source = "phone", target = "telefono")
    @Mapping(source = "userRegistration", target = "usuarioRegistro")
    UsernameResponseByIdDto.UsernameResponseByIdPhoneDto mapPhoneEntityToUsernameResponseByIdPhoneDto(
            PhoneEntity phoneEntity);

    @Mapping(source = "idRole", target = "codigo")
    @Mapping(source = "role", target = "rol")
    @Mapping(source = "description", target = "descripcion")
    UsernameResponseByIdDto.UsernameResponseByIdRoleDto mapRoleEntityToUsernameResponseByIdRoleDto(
            RoleEntity roleEntity);

    @Mapping(source = "idUsername", target = "codigo")
    @Mapping(source = "personEntity.idPerson", target = "persona.codigo")
    @Mapping(source = "personEntity.firstName", target = "persona.nombre")
    @Mapping(source = "personEntity.lastName", target = "persona.apellidoPaterno")
    @Mapping(source = "personEntity.secondLastName", target = "persona.apellidoMaterno")
    @Mapping(source = "personEntity.nationalityEntity.idNationality", target = "persona.nacionalidad.codigo")
    @Mapping(source = "personEntity.nationalityEntity.country", target = "persona.nacionalidad.nacionalidad")
    @Mapping(source = "personEntity.genderEntity.idGender", target = "persona.genero.codigo")
    @Mapping(source = "personEntity.genderEntity.gender", target = "persona.genero.genero")
    @Mapping(source = "personEntity.typeDocumentEntity.idTypeDocument", target = "persona.tipoDocumento.codigo")
    @Mapping(source = "personEntity.typeDocumentEntity.typeDocument", target = "persona.tipoDocumento.tipoDocumento")
    @Mapping(source = "personEntity.doi", target = "persona.numeroDocumento")
    @Mapping(source = "personEntity.birthday", target = "persona.onomastico")
    @Mapping(source = "personEntity.userRegistration", target = "persona.usuarioRegistro")
    @Mapping(source = "typeContractEntity.idTypeContract", target = "tipoContratacion.codigo")
    @Mapping(source = "typeContractEntity.typeContract", target = "tipoContratacion.nombre")
    @Mapping(source = "username", target = "nombreUsuario")
    UsernameResponseByIdDto mapUsernameEntityToUsernameResponseByIdDtoSimple(UsernameEntity usernameEntity);

    default UsernameResponseByIdDto mapUsernameEntityToUsernameResponseByIdDto(UsernameEntity usernameEntity) {
        UsernameResponseByIdDto usernameResponseByIdDto = mapUsernameEntityToUsernameResponseByIdDtoSimple(
                usernameEntity);
        List<UsernameResponseByIdDto.UsernameResponseByIdEmailDto> usernameResponseByIdEmailDtos = new ArrayList<>();
        List<UsernameResponseByIdDto.UsernameResponseByIdPhoneDto> usernameResponseByIdPhoneDtos = new ArrayList<>();
        List<UsernameResponseByIdDto.UsernameResponseByIdRoleDto> usernameResponseByIdRoleDtos = new ArrayList<>();
        usernameEntity.getPersonEntity().getEmailEntityList().forEach(emailEntity ->
                usernameResponseByIdEmailDtos.add(mapEmailEntityToUsernameResponseByIdEmailDto(emailEntity)));
        usernameResponseByIdDto.getPersona().setCorreos(usernameResponseByIdEmailDtos);
        usernameEntity.getPersonEntity().getPhoneEntityList().forEach(phoneEntity ->
                usernameResponseByIdPhoneDtos.add(mapPhoneEntityToUsernameResponseByIdPhoneDto(phoneEntity)));
        usernameResponseByIdDto.getPersona().setTelefonos(usernameResponseByIdPhoneDtos);
        usernameEntity.getRoles().forEach(roleEntity ->
                usernameResponseByIdRoleDtos.add(mapRoleEntityToUsernameResponseByIdRoleDto(roleEntity)));
        usernameResponseByIdDto.setRoles(usernameResponseByIdRoleDtos);
        return usernameResponseByIdDto;
    }

}