package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.categoryticket.CategoryTicketDefaultDto;
import com.cdc.inlog.pe.dto.categoryticket.CategoryTicketResponseByIdDto;
import com.cdc.inlog.pe.entity.CategoryTicketEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryTicketMapper {

    @Mapping(source = "idCategoryTicket", target = "codigo")
    @Mapping(source = "categoryTicket", target = "nombre")
    CategoryTicketDefaultDto mapCategoryTicketEntityToCategoryTicketDefaultDto(CategoryTicketEntity categoryTicketEntity);

    List<CategoryTicketDefaultDto> mapListCategoryTicketEntityToCategoryTicketDefaultDto(List<CategoryTicketEntity> categoryTicketEntities);

    @Mapping(source = "idCategoryTicket", target = "codigo")
    @Mapping(source = "categoryTicket", target = "nombre")
    CategoryTicketResponseByIdDto mapCategoryTicketEntityToCategoryResponseByIdDto(CategoryTicketEntity categoryTicketEntity);

}