package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.typedocument.TypeDocumentDefaultDto;
import com.cdc.inlog.pe.entity.TypeDocumentEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeDocumentMapper {

    @Mapping(source = "idTypeDocument", target = "codigo")
    @Mapping(source = "typeDocument", target = "nombre")
    TypeDocumentDefaultDto mapTypeDocumentEntityToTypeDocumentDefaultDto(TypeDocumentEntity typeDocumentEntity);

    List<TypeDocumentDefaultDto> mapListTypeDocumentEntityToTypeDocumentDefaultDto(List<TypeDocumentEntity> typeDocumentEntities);

}