package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.email.*;
import com.cdc.inlog.pe.entity.EmailEntity;
import java.time.LocalDate;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    @Mapping(source = "emailEntity.idEmail", target = "codigo")
    @Mapping(source = "emailEntity.typeEmailEntity.typeEmail", target = "tipoCorreo")
    @Mapping(expression = "java(emailEntity.getPersonEntity().getFullName())", target = "nombrePersona")
    @Mapping(source = "emailEntity.email", target = "correo")
    EmailDefaultDto mapEmailEntityToEmailDefaultDto(EmailEntity emailEntity);

    List<EmailDefaultDto> mapListEmailEntityToEmailDefaultDto(List<EmailEntity> emailEntities);

    @Mapping(source = "emailRequestDto.tipoCorreo.codigo", target = "typeEmailEntity.idTypeEmail")
    @Mapping(source = "emailRequestDto.persona.codigo", target = "personEntity.idPerson")
    @Mapping(source = "emailRequestDto.correo", target = "email")
    @Mapping(source = "emailRequestDto.usuarioRegistro", target = "userRegistration")
    @Mapping(constant = "true", target = "active")
    @Mapping(constant = "true", target = "userActive")
    @Mapping(source = "now", target = "dateRegister")
    EmailEntity mapEmailRequestDtoToEmailEntity(EmailRequestDto emailRequestDto, LocalDate now);

    @Mapping(source = "typeEmailEntity.idTypeEmail", target = "tipoCorreo.nombre")
    @Mapping(expression = "java(emailEntity.getPersonEntity().getFullName())", target = "persona.nombre")
    EmailResponseDto mapEmailEntityToEmailResponseDto(EmailEntity emailEntity);

    @Mapping(source = "emailEntity.idEmail", target = "codigo")
    @Mapping(source = "emailEntity.typeEmailEntity.idTypeEmail", target = "tipoCorreo.codigo")
    @Mapping(source = "emailEntity.typeEmailEntity.typeEmail", target = "tipoCorreo.nombre")
    @Mapping(source = "emailEntity.email", target = "correo")
    @Mapping(source = "emailEntity.personEntity.idPerson", target = "persona.codigo")
    @Mapping(source = "emailEntity.userRegistration", target = "usuarioRegistro")
    @Mapping(source = "emailEntity.dateRegister", target = "fechaRegistro")
    EmailResponseByIdDto mapEmailEntityToEmailResponseByIdDtoSimple(EmailEntity emailEntity);

    default EmailResponseByIdDto mapEmailEntityToEmailResponseByIdDto(EmailEntity emailEntity) {
        EmailResponseByIdDto emailResponseByIdDto = mapEmailEntityToEmailResponseByIdDtoSimple(emailEntity);
        emailResponseByIdDto.getPersona().setNombre(emailEntity.getPersonEntity().getFullName());
        return emailResponseByIdDto;
    }

    @Mapping(source = "codigo", target = "idEmail")
    @Mapping(source = "emailUpdateDto.tipoCorreo.codigo", target = "typeEmailEntity.idTypeEmail")
    @Mapping(source = "emailUpdateDto.correo", target = "email")
    EmailEntity mapEmailUpdateDtoToEmailEntity(EmailUpdateDto emailUpdateDto, Integer codigo);

}