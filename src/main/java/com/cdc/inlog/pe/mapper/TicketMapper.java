package com.cdc.inlog.pe.mapper;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;

import com.cdc.inlog.pe.dto.ticket.*;
import com.cdc.inlog.pe.entity.DetailTicketEntity;
import com.cdc.inlog.pe.entity.TicketEntity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "idTicket", target = "codigo")
    @Mapping(source = "categoryTicketEntity.categoryTicket", target = "categoriaTicket")
    @Mapping(source = "priorityEntity.priority", target = "prioridad.prioridad")
    @Mapping(source = "priorityEntity.codeColor", target = "prioridad.codigoColor")
    @Mapping(source = "usernameEntity.username", target = "datosUsuario.usuario")
    @Mapping(expression = "java(usernameEntity.getPersonEntity().getFullName())", target = "datosUsuario.nombrePersona")
    @Mapping(source = "subject", target = "asunto")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "dateRegister", target = "fechoraCreacion")
    TicketDefaultDto mapTicketEntityToTicketDefaultDtoSimple(TicketEntity ticketEntity);

    @Named(value = "ticketDefaultDto")
    default TicketDefaultDto mapTicketEntityToTicketDefaultDto(TicketEntity ticketEntity) {
        TicketDefaultDto ticketDefaultDto = mapTicketEntityToTicketDefaultDtoSimple(ticketEntity);
        DetailTicketEntity detailTicketEntityAux = ticketEntity.getDetailTicketEntityList()
                .stream()
                .filter(DetailTicketEntity::isActive)
                .max(Comparator.comparing(
                        detailTicketEntity -> detailTicketEntity.getStatusTicketEntity().getIdStatusTicket()))
                .orElse(new DetailTicketEntity());
        TicketDefaultDto.TicketDefaultUsernameDto ticketDefaultUsernameDto = new TicketDefaultDto.TicketDefaultUsernameDto();
        if (detailTicketEntityAux.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_ONE)) {
            ticketDefaultUsernameDto.setUsuario("SIN ASIGNAR");
            ticketDefaultUsernameDto.setNombrePersona("SIN ASIGNAR");
        } else {
            ticketDefaultUsernameDto.setUsuario(detailTicketEntityAux.getUsernameEntity().getUsername());
            ticketDefaultUsernameDto.setNombrePersona(detailTicketEntityAux.getUsernameEntity().getPersonEntity().getFullName());
        }
        ticketDefaultDto.setAsesorAsignado(ticketDefaultUsernameDto);
        TicketDefaultDto.TicketDefaultDetailTicketDto ticketDefaultDetailTicketDto = new TicketDefaultDto.TicketDefaultDetailTicketDto();
        ticketDefaultDetailTicketDto.setEstado(detailTicketEntityAux.getStatusTicketEntity().getStatusTicket());
        ticketDefaultDetailTicketDto.setCodigoColor(detailTicketEntityAux.getStatusTicketEntity().getCodeColor());
        ticketDefaultDto.setDatosAtencion(ticketDefaultDetailTicketDto);
        return ticketDefaultDto;
    }

    @IterableMapping(qualifiedByName = "ticketDefaultDto")
    List<TicketDefaultDto> mapListTicketEntityToTicketDefaultDto(List<TicketEntity> ticketEntities);

    @Mapping(source = "idDetailTicket", target = "codigo")
    @Mapping(source = "statusTicketEntity.idStatusTicket", target = "datosEstadoTicket.codigo")
    @Mapping(source = "statusTicketEntity.statusTicket", target = "datosEstadoTicket.nombre")
    @Mapping(source = "statusTicketEntity.codeColor", target = "datosEstadoTicket.codigoColor")
    @Mapping(source = "usernameEntity.idUsername", target = "datosUsuario.codigo")
    @Mapping(source = "usernameEntity.username", target = "datosUsuario.usuario")
    @Mapping(expression = "java(usernameEntity.getPersonEntity().getFullName())", target = "datosUsuario.nombrePersona")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "observation", target = "observacion")
    @Mapping(source = "dateRegister", target = "fechaRegistro")
    TicketResponseByIdDto.TicketResponseByIdDetailTicketDto mapDetailTicketToTicketResponseByIdDetailTicketDto(
            DetailTicketEntity detailTicketEntity);

    @Mapping(source = "idTicket", target = "codigo")
    @Mapping(source = "categoryTicketEntity.idCategoryTicket", target = "datosCategoriaTicket.codigo")
    @Mapping(source = "categoryTicketEntity.categoryTicket", target = "datosCategoriaTicket.nombre")
    @Mapping(source = "priorityEntity.idPriority", target = "datosPrioridad.codigo")
    @Mapping(source = "priorityEntity.priority", target = "datosPrioridad.prioridad")
    @Mapping(source = "priorityEntity.codeColor", target = "datosPrioridad.codigoColor")
    @Mapping(source = "usernameEntity.idUsername", target = "datosUsuario.codigo")
    @Mapping(source = "usernameEntity.username", target = "datosUsuario.usuario")
    @Mapping(expression = "java(usernameEntity.getPersonEntity().getFullName())", target = "datosUsuario.nombrePersona")
    @Mapping(source = "subject", target = "asunto")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "observation", target = "observacion")
    /*@Mapping(source = "observation", target = "asesorAsignado.codigo")
    @Mapping(source = "observation", target = "asesorAsignado.nombre")*/
    @Mapping(source = "dateRegister", target = "fechaRegistro")
    TicketResponseByIdDto mapTicketEntityToTicketResponseByIdDtoSimple(TicketEntity ticketEntity);

    default TicketResponseByIdDto mapTicketEntityToTicketResponseByIdDto(TicketEntity ticketEntity) {
        TicketResponseByIdDto ticketResponseByIdDto = mapTicketEntityToTicketResponseByIdDtoSimple(ticketEntity);
        List<TicketResponseByIdDto.TicketResponseByIdDetailTicketDto> list = new ArrayList<>();
        ticketEntity.getDetailTicketEntityList()
                .forEach(detailTicketEntity -> list.add(
                        mapDetailTicketToTicketResponseByIdDetailTicketDto(detailTicketEntity)));
        ticketResponseByIdDto.setDetallesTicket(list);
        return ticketResponseByIdDto;
    }

    @Mapping(expression = "java(com.cdc.inlog.pe.util.Constants.NUMBER_NINE)", target = "categoryTicketEntity.idCategoryTicket")
    @Mapping(expression = "java(com.cdc.inlog.pe.util.Constants.NUMBER_FOUR)", target = "priorityEntity.idPriority")
    @Mapping(source = "ticketRequestDto.usuarioRegistro.codigo", target = "usernameEntity.idUsername")
    @Mapping(source = "ticketRequestDto.asunto", target = "subject")
    @Mapping(source = "ticketRequestDto.descripcion", target = "description")
    @Mapping(source = "ticketRequestDto.observacion", target = "observation")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    TicketEntity mapTicketRequestDtoToTicketEntitySimple(TicketRequestDto ticketRequestDto, LocalDateTime now);

    @Mapping(source = "ticketEntity", target = "ticketEntity")
    @Mapping(expression = "java(com.cdc.inlog.pe.util.Constants.NUMBER_ONE)",
            target = "statusTicketEntity.idStatusTicket")
    @Mapping(source = "ticketEntity.usernameEntity.idUsername", target = "usernameEntity.idUsername")
    @Mapping(expression = "java(com.cdc.inlog.pe.util.Constants.DEFAULT_DESCRIPTION_TICKET)", target = "description")
    @Mapping(expression = "java(com.cdc.inlog.pe.util.Constants.DEFAULT_OBSERVATION)", target = "observation")
    @Mapping(constant = "true", target = "active")
    @Mapping(source = "now", target = "dateRegister")
    DetailTicketEntity mapTicketEntityToDetailTicketEntityAux(TicketEntity ticketEntity, LocalDateTime now);

    default TicketEntity mapTicketRequestDtoToTicketEntity(TicketRequestDto ticketRequestDto) {
        TicketEntity ticketEntity = mapTicketRequestDtoToTicketEntitySimple(ticketRequestDto,
                LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU)));
        List<DetailTicketEntity> detailTicketEntities = new ArrayList<>();
        detailTicketEntities.add(mapTicketEntityToDetailTicketEntityAux(ticketEntity,
                LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU))));
        ticketEntity.setDetailTicketEntityList(detailTicketEntities);
        return ticketEntity;
    }

    @Mapping(source = "categoryTicketEntity.categoryTicket", target = "categoriaTicket")
    @Mapping(source = "usernameEntity.username", target = "usuarioRegistro")
    TicketResponseDto mapTicketEntityToTicketResponseDto(TicketEntity ticketEntity);

    @Mapping(source = "codigo", target = "idTicket")
    @Mapping(source = "ticketUpdateDto.codigoCategoriaTicket", target = "categoryTicketEntity.idCategoryTicket")
    @Mapping(source = "ticketUpdateDto.descripcion", target = "description")
    @Mapping(source = "ticketUpdateDto.observacion", target = "observation")
    TicketEntity mapTicketUpdateDtoToTicketEntity(TicketUpdateDto ticketUpdateDto, Integer codigo);

}