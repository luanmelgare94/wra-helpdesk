package com.cdc.inlog.pe.mapper;

import java.time.LocalDateTime;
import com.cdc.inlog.pe.dto.detailticket.DetailTicketRequestDto;
import com.cdc.inlog.pe.dto.detailticket.DetailTicketResponseByIdDto;
import com.cdc.inlog.pe.dto.detailticket.DetailTicketResponseDto;
import com.cdc.inlog.pe.dto.detailticket.DetailTicketUpdateDto;
import com.cdc.inlog.pe.entity.DetailTicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailTicketMapper {

    @Mapping(source = "detailTicketRequestDto.codigoTicket", target = "ticketEntity.idTicket")
    @Mapping(source = "detailTicketRequestDto.codigoEstadoTicket", target = "statusTicketEntity.idStatusTicket")
    @Mapping(source = "detailTicketRequestDto.usuario", target = "usernameEntity.idUsername")
    @Mapping(source = "detailTicketRequestDto.descripcion", target = "description")
    @Mapping(source = "detailTicketRequestDto.observacion", target = "observation")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    DetailTicketEntity mapDetailTicketRequestDtoToDetailTicketEntity(DetailTicketRequestDto detailTicketRequestDto,
                                                                     LocalDateTime now);

    @Mapping(source = "usernameEntity.username", target = "nombreUsuario")
    @Mapping(source = "statusTicketEntity.statusTicket", target = "estadoTicket")
    DetailTicketResponseDto mapDetailTicketEntityToDetailTicketResponseDto(DetailTicketEntity detailTicketEntity);

    @Mapping(source = "idDetailTicket", target = "codigo")
    @Mapping(source = "ticketEntity.categoryTicketEntity.categoryTicket", target = "datosTicket.categoriaTicket")
    @Mapping(source = "ticketEntity.priorityEntity.priority", target = "datosTicket.prioridad")
    @Mapping(source = "ticketEntity.usernameEntity.username", target = "datosTicket.datosUsuario.usuario")
    @Mapping(expression = "java(usernameEntity.getPersonEntity().getFullName())", target = "datosTicket.datosUsuario.nombrePersona")
    @Mapping(source = "ticketEntity.dateRegister.", target = "datosTicket.fechoraRegistro")
    @Mapping(source = "statusTicketEntity.statusTicket", target = "datosEstadoTicket.estadoTicket")
    @Mapping(source = "statusTicketEntity.codeColor", target = "datosEstadoTicket.codigoColor")
    @Mapping(source = "usernameEntity.username", target = "datosUsuario.usuario")
    @Mapping(expression = "java(usernameEntity.getPersonEntity().getFullName())", target = "datosUsuario.nombrePersona")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "observation", target = "observacion")
    @Mapping(source = "dateRegister", target = "fechoraRegistro")
    DetailTicketResponseByIdDto mapDetailTicketEntityToDetailTicketResponseByIdDto(DetailTicketEntity detailTicketEntity);

    @Mapping(source = "codigo", target = "idDetailTicket")
    @Mapping(source = "detailTicketUpdateDto.descripcion", target = "description")
    @Mapping(source = "detailTicketUpdateDto.observacion", target = "observation")
    DetailTicketEntity mapDetailTicketUpdateDtoToDetailTicketEntity(DetailTicketUpdateDto detailTicketUpdateDto,
                                                                    Integer codigo);

}