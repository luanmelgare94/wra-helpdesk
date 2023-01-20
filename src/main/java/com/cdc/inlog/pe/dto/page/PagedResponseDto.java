package com.cdc.inlog.pe.dto.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedResponseDto<T> {

    private List<T> content;

    private PageableDto pageable;

    private boolean last;

    private Integer totalPages;

    private Long totalElements;

    private Integer size;

    private Integer number;

    private SortDto sort;

    private boolean first;

    private Integer numberOfElements;

    private boolean empty;

    public PagedResponseDto(List<T> content, PageableDto pageable, boolean last, Integer totalPages, Long totalElements,
                            Integer size, Integer number, SortDto sort, boolean first,
                            Integer numberOfElements, boolean empty) {
        this.content = content;
        this.pageable = pageable;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
        this.sort = sort;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.empty = empty;
    }

    @Getter
    @Setter
    public static class PageableDto {
        private SortDto sort;
        private long offset;
        private long pageNumber;
        private long pageSize;
        private boolean paged;
        private boolean unpaged;
    }

    @Getter
    @Setter
    public static class SortDto {
        private boolean empty;
        private boolean sorted;
        private boolean unsorted;
    }

}