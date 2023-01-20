package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.phone.*;
import com.cdc.inlog.pe.entity.PhoneEntity;

import java.time.LocalDate;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    @Mapping(source = "phoneEntity.idPhone", target = "codigo")
    @Mapping(source = "phoneEntity.typePhoneEntity.typePhone", target = "tipoTelefono")
    @Mapping(expression = "java(phoneEntity.getPersonEntity().getFullName())", target = "nombrePersona")
    @Mapping(source = "phoneEntity.operatorEntity.operator", target = "operadorTelefonico")
    @Mapping(source = "phoneEntity.phone", target = "telefono")
    PhoneDefaultDto mapPhoneEntityToPhoneDefaultDto(PhoneEntity phoneEntity);

    List<PhoneDefaultDto> mapListPhoneEntityToPhoneDefaultDto(List<PhoneEntity> phoneEntities);

    @Mapping(source = "phoneRequestDto.tipoTelefono.codigo", target = "typePhoneEntity.idTypePhone")
    @Mapping(source = "phoneRequestDto.persona.codigo", target = "personEntity.idPerson")
    @Mapping(source = "phoneRequestDto.operadorTelefonico.codigo", target = "operatorEntity.idOperator")
    @Mapping(source = "phoneRequestDto.telefono", target = "phone")
    @Mapping(source = "phoneRequestDto.usuarioRegistro", target = "userRegistration")
    @Mapping(source = "phoneRequestDto.observacion", target = "observation")
    @Mapping(constant = "true", target = "active")
    @Mapping(constant = "true", target = "userActive")
    @Mapping(source = "now", target = "dateRegister")
    PhoneEntity mapPhoneRequestDtoToPhoneEntity(PhoneRequestDto phoneRequestDto, LocalDate now);

    @Mapping(source = "typePhoneEntity.typePhone", target = "tipoTelefono.nombre")
    @Mapping(source = "operatorEntity.operator", target = "operadorTelefonico.nombre")
    @Mapping(expression = "java(phoneEntity.getPersonEntity().getFullName())", target = "persona.nombre")
    PhoneResponseDto mapPhoneEntityToPhoneResponseDto(PhoneEntity phoneEntity);

    @Mapping(source = "idPhone", target = "codigo")
    @Mapping(source = "typePhoneEntity.idTypePhone", target = "tipoTelefono.codigo")
    @Mapping(source = "typePhoneEntity.typePhone", target = "tipoTelefono.nombre")
    @Mapping(source = "personEntity.idPerson", target = "persona.codigo")
    @Mapping(expression = "java(personEntity.getFullName())", target = "persona.nombre")
    @Mapping(source = "operatorEntity.idOperator", target = "operadorTelefonico.codigo")
    @Mapping(source = "operatorEntity.operator", target = "operadorTelefonico.nombre")
    @Mapping(source = "phone", target = "telefono")
    @Mapping(source = "userRegistration", target = "usuarioRegistro")
    @Mapping(source = "observation", target = "observacion")
    @Mapping(source = "dateRegister", target = "fechaRegistro")
    PhoneResponseByIdDto mapPhoneEntityToPhoneResponseByIdDto(PhoneEntity phoneEntity);

    @Mapping(source = "codigo", target = "idPhone")
    @Mapping(source = "phoneUpdateDto.tipoTelefono.codigo", target = "typePhoneEntity.idTypePhone")
    @Mapping(source = "phoneUpdateDto.operadorTelefonico.codigo", target = "operatorEntity.idOperator")
    @Mapping(source = "phoneUpdateDto.telefono", target = "phone")
    PhoneEntity mapPhoneUpdateDtoToPhoneEntity(PhoneUpdateDto phoneUpdateDto, Integer codigo);

}