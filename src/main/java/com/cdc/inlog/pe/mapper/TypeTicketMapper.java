package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.typeticket.TypeTicketDefaultDto;
import com.cdc.inlog.pe.dto.typeticket.TypeTicketResponseByIdDto;
import com.cdc.inlog.pe.entity.TypeTicketEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeTicketMapper {

    @Mapping(source = "idTypeTicket", target = "codigo")
    @Mapping(source = "typeTicket", target = "nombre")
    TypeTicketDefaultDto mapTypeTicketEntityToTypeTicketDefaultDto(TypeTicketEntity typeTicketEntity);

    List<TypeTicketDefaultDto> mapListTypeTicketEntityToTypeTicketDefaultDto(List<TypeTicketEntity> typeTicketEntities);

    @Mapping(source = "idTypeTicket", target = "codigo")
    @Mapping(source = "typeTicket", target = "nombre")
    TypeTicketResponseByIdDto mapTypeTicketEntityTypeToTicketResponseByIdDto(TypeTicketEntity typeTicketEntity);

}