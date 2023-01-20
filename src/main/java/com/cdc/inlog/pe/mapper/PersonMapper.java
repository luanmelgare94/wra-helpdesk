package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.person.PersonDefaultDto;
import com.cdc.inlog.pe.dto.person.PersonResponseByIdDto;
import com.cdc.inlog.pe.entity.EmailEntity;
import com.cdc.inlog.pe.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

import com.cdc.inlog.pe.entity.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source = "personEntity.idPerson", target = "codigo")
    @Mapping(source = "personEntity.lastName", target = "apellidoPaterno")
    @Mapping(source = "personEntity.secondLastName", target = "apellidoMaterno")
    @Mapping(source = "personEntity.firstName", target = "nombres")
    @Mapping(source = "personEntity.nationalityEntity.country", target = "nacionalidad")
    @Mapping(source = "personEntity.genderEntity.gender", target = "genero")
    @Mapping(source = "personEntity.typeDocumentEntity.typeDocument", target = "tipoDocumento")
    @Mapping(source = "personEntity.doi", target = "numeroDocumento")
    PersonDefaultDto mapPersonEntityToPersonDefaultDto(PersonEntity personEntity);

    List<PersonDefaultDto> mapListPersonEntityToPersonDefaultDto(List<PersonEntity> personEntities);

    @Mapping(source = "idEmail", target = "codigo")
    @Mapping(source = "typeEmailEntity.idTypeEmail", target = "tipoCorreo.codigo")
    @Mapping(source = "typeEmailEntity.typeEmail", target = "tipoCorreo.nombre")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "userRegistration", target = "usuarioRegistro")
    @Mapping(source = "dateRegister", target = "fechaRegistro")
    PersonResponseByIdDto.PersonResponseByIdEmailDto mapEmailEntityToPersonResponseByIdEmailDto(EmailEntity emailEntity);

    @Mapping(source = "idPhone", target = "codigo")
    @Mapping(source = "typePhoneEntity.idTypePhone", target = "tipoTelefono.codigo")
    @Mapping(source = "typePhoneEntity.typePhone", target = "tipoTelefono.nombre")
    @Mapping(source = "operatorEntity.idOperator", target = "operadorTelefonico.codigo")
    @Mapping(source = "operatorEntity.operator", target = "operadorTelefonico.nombre")
    @Mapping(source = "phone", target = "telefono")
    @Mapping(source = "userRegistration", target = "usuarioRegistro")
    @Mapping(source = "observation", target = "observacion")
    @Mapping(source = "dateRegister", target = "fechaRegistro")
    PersonResponseByIdDto.PersonResponseByIdPhoneDto mapPhoneEntityToPersonResponseByIdPhoneDto(PhoneEntity phoneEntity);

    @Mapping(source = "idPerson", target = "codigo")
    @Mapping(source = "firstName", target = "nombre")
    @Mapping(source = "lastName", target = "apellidoPaterno")
    @Mapping(source = "secondLastName", target = "apellidoMaterno")
    @Mapping(source = "nationalityEntity.idNationality", target = "nacionalidad.codigo")
    @Mapping(source = "nationalityEntity.country", target = "nacionalidad.nombre")
    @Mapping(source = "genderEntity.idGender", target = "genero.codigo")
    @Mapping(source = "genderEntity.gender", target = "genero.nombre")
    @Mapping(source = "typeDocumentEntity.idTypeDocument", target = "tipoDocumento.codigo")
    @Mapping(source = "typeDocumentEntity.typeDocument", target = "tipoDocumento.nombre")
    @Mapping(source = "doi", target = "numeroDocumento")
    @Mapping(source = "birthday", target = "onomastico")
    @Mapping(source = "userRegistration", target = "usuarioRegistro")
    PersonResponseByIdDto mapPersonEntityToPersonResponseByIdDtoSimple(PersonEntity personEntity);

    default PersonResponseByIdDto mapPersonEntityToPersonResponseByIdDto(PersonEntity personEntity) {
        List<PersonResponseByIdDto.PersonResponseByIdPhoneDto> personResponseByIdPhoneDtos = new ArrayList<>();
        List<PersonResponseByIdDto.PersonResponseByIdEmailDto> personResponseByIdEmailDtos = new ArrayList<>();
        PersonResponseByIdDto personResponseByIdDto = mapPersonEntityToPersonResponseByIdDtoSimple(personEntity);
        personEntity.getPhoneEntityList().forEach(phoneEntity -> personResponseByIdPhoneDtos.add(mapPhoneEntityToPersonResponseByIdPhoneDto(phoneEntity)));
        personEntity.getEmailEntityList().forEach(emailEntity -> personResponseByIdEmailDtos.add(mapEmailEntityToPersonResponseByIdEmailDto(emailEntity)));
        personResponseByIdDto.setTelefonos(personResponseByIdPhoneDtos);
        personResponseByIdDto.setCorreos(personResponseByIdEmailDtos);
        return personResponseByIdDto;
    }

}