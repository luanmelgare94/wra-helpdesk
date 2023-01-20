package com.cdc.inlog.pe.mapper;

import com.cdc.inlog.pe.dto.page.PagedResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mapping(source = "pageable.sort.empty", target = "sort.empty")
    @Mapping(source = "pageable.sort.sorted", target = "sort.sorted")
    @Mapping(source = "pageable.sort.unsorted", target = "sort.unsorted")
    @Mapping(source = "pageable.offset", target = "offset")
    @Mapping(source = "pageable.pageNumber", target = "pageNumber")
    @Mapping(source = "pageable.pageSize", target = "pageSize")
    @Mapping(source = "pageable.paged", target = "paged")
    @Mapping(source = "pageable.unpaged", target = "unpaged")
    PagedResponseDto.PageableDto mapPageableToPageableDto(Pageable pageable);

    @Mapping(source = "pageable.sort.empty", target = "empty")
    @Mapping(source = "pageable.sort.sorted", target = "sorted")
    @Mapping(source = "pageable.sort.unsorted", target = "unsorted")
    PagedResponseDto.SortDto mapSortToSortDto(Pageable pageable);

}