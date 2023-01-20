package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.statusticket.StatusTicketDefaultDto;
import com.cdc.inlog.pe.dto.statusticket.StatusTicketResponseByIdDto;
import com.cdc.inlog.pe.entity.StatusTicketEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusTicketMapper {

    @Mapping(source = "idStatusTicket", target = "codigo")
    @Mapping(source = "statusTicket", target = "nombre")
    StatusTicketDefaultDto mapStatusTicketEntityToStatusTicketDefaultDto(StatusTicketEntity statusTicketEntity);

    List<StatusTicketDefaultDto> mapListStatusTicketEntityToStatusTicketDefaultDto(List<StatusTicketEntity>
                                                                                           statusTicketEntities);

    @Mapping(source = "idStatusTicket", target = "codigo")
    @Mapping(source = "statusTicket", target = "nombre")
    @Mapping(source = "codeColor", target = "codigoColor")
    StatusTicketResponseByIdDto mapStatusTicketEntityToStatusTicketResponseByIdDto(StatusTicketEntity
                                                                                           statusTicketEntity);

}