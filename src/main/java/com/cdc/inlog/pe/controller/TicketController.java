package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_TICKET;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_EMPTY;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED_BY_USERNAME;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED_BY_USERNAME;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_PATCH_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_REGISTER;
import static com.cdc.inlog.pe.util.Constants.SUB_API_TICKET_IS_CATEGORIZED_AND_PRIORITIZED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_TICKET_UPDATE_CATEGORY_AND_PRIORITY;

import com.cdc.inlog.pe.dto.page.PagedResponseDto;
import com.cdc.inlog.pe.dto.ticket.*;
import com.cdc.inlog.pe.entity.TicketEntity;
import com.cdc.inlog.pe.mapper.PageMapper;
import com.cdc.inlog.pe.mapper.TicketMapper;
import com.cdc.inlog.pe.service.TicketService;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = API_TICKET)
public class TicketController {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PageMapper pageMapper;

    @GetMapping(path = SUB_API_ACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedResponseDto<TicketDefaultDto>> getAllTicketActivated(Pageable pageable) {
        log.info("TicketController.getAllTicketActivated");
        log.info("TicketController.getAllTicketActivated.pageNumber: " + pageable.getPageNumber());
        log.info("TicketController.getAllTicketActivated.pageSize: " + pageable.getPageSize());
        Page<TicketEntity> ticketEntityPage = ticketService.getAllEntityActivated(pageable);
        return new ResponseEntity<>(new PagedResponseDto<TicketDefaultDto>(
                ticketMapper.mapListTicketEntityToTicketDefaultDto(ticketEntityPage.getContent()),
                pageMapper.mapPageableToPageableDto(ticketEntityPage.getPageable()),
                ticketEntityPage.isLast(), ticketEntityPage.getTotalPages(),
                ticketEntityPage.getTotalElements(), ticketEntityPage.getSize(), ticketEntityPage.getNumber(),
                pageMapper.mapSortToSortDto(ticketEntityPage.getPageable()), ticketEntityPage.isFirst(),
                ticketEntityPage.getNumberOfElements(), ticketEntityPage.isEmpty()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_ACTIVATED_BY_USERNAME, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedResponseDto<TicketDefaultDto>> getAllTicketActivatedByUsername(Pageable pageable,
                                                                                              @PathVariable String username) {
        log.info("TicketController.getAllTicketActivatedByUsername");
        log.info("TicketController.getAllTicketActivatedByUsername.pageNumber: " + pageable.getPageNumber());
        log.info("TicketController.getAllTicketActivatedByUsername.pageSize: " + pageable.getPageSize());
        log.info("TicketController.getAllTicketActivatedByUsername.username: " + username);
        Page<TicketEntity> ticketEntityPage = ticketService.getAllEntityActivatedByUsername(pageable, username);
        return new ResponseEntity<>(new PagedResponseDto<TicketDefaultDto>(
                ticketMapper.mapListTicketEntityToTicketDefaultDto(ticketEntityPage.getContent()),
                pageMapper.mapPageableToPageableDto(ticketEntityPage.getPageable()),
                ticketEntityPage.isLast(), ticketEntityPage.getTotalPages(),
                ticketEntityPage.getTotalElements(), ticketEntityPage.getSize(), ticketEntityPage.getNumber(),
                pageMapper.mapSortToSortDto(ticketEntityPage.getPageable()), ticketEntityPage.isFirst(),
                ticketEntityPage.getNumberOfElements(), ticketEntityPage.isEmpty()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedResponseDto<TicketDefaultDto>> getAllTicketInactivated(Pageable pageable) {
        log.info("TicketController.getAllTicketInactivated");
        log.info("TicketController.getAllTicketInactivated.pageNumber: " + pageable.getPageNumber());
        log.info("TicketController.getAllTicketInactivated.pageSize: " + pageable.getPageSize());
        Page<TicketEntity> ticketEntityPage = ticketService.getAllEntityDeactivated(pageable);
        return new ResponseEntity<>(new PagedResponseDto<TicketDefaultDto>(
                ticketMapper.mapListTicketEntityToTicketDefaultDto(ticketEntityPage.getContent()),
                pageMapper.mapPageableToPageableDto(ticketEntityPage.getPageable()),
                ticketEntityPage.isLast(), ticketEntityPage.getTotalPages(),
                ticketEntityPage.getTotalElements(), ticketEntityPage.getSize(), ticketEntityPage.getNumber(),
                pageMapper.mapSortToSortDto(ticketEntityPage.getPageable()), ticketEntityPage.isFirst(),
                ticketEntityPage.getNumberOfElements(), ticketEntityPage.isEmpty()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_INACTIVATED_BY_USERNAME, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedResponseDto<TicketDefaultDto>> getAllTicketInactivatedByUsername(Pageable pageable,
                                                                                                @PathVariable String username) {
        log.info("TicketController.getAllTicketInactivatedByUsername");
        log.info("TicketController.getAllTicketInactivatedByUsername.pageNumber: " + pageable.getPageNumber());
        log.info("TicketController.getAllTicketInactivatedByUsername.pageSize: " + pageable.getPageSize());
        log.info("TicketController.getAllTicketInactivatedByUsername.username: " + username);
        Page<TicketEntity> ticketEntityPage = ticketService.getAllEntityDeactivatedByUsername(pageable, username);
        return new ResponseEntity<>(new PagedResponseDto<TicketDefaultDto>(
                ticketMapper.mapListTicketEntityToTicketDefaultDto(ticketEntityPage.getContent()),
                pageMapper.mapPageableToPageableDto(ticketEntityPage.getPageable()),
                ticketEntityPage.isLast(), ticketEntityPage.getTotalPages(),
                ticketEntityPage.getTotalElements(), ticketEntityPage.getSize(), ticketEntityPage.getNumber(),
                pageMapper.mapSortToSortDto(ticketEntityPage.getPageable()), ticketEntityPage.isFirst(),
                ticketEntityPage.getNumberOfElements(), ticketEntityPage.isEmpty()), HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketResponseDto> registerTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        log.info("TicketController.registerTicket");
        return new ResponseEntity<>(ticketMapper.mapTicketEntityToTicketResponseDto(
                ticketService.registerEntity(
                        ticketMapper.mapTicketRequestDtoToTicketEntity(ticketRequestDto))), HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketResponseByIdDto> getTicketById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                               @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("TicketController.getTicketById");
        log.info("TicketController.getTicketById.codigo: " + codigo);
        return ticketService.existsEntityById(codigo) ? new ResponseEntity<>(
                ticketMapper.mapTicketEntityToTicketResponseByIdDto(ticketService.getAllEntityById(codigo)),
                HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = SUB_API_TICKET_IS_CATEGORIZED_AND_PRIORITIZED, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> isCategorizedAndPrioritizedByIdTicket(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                                             Integer codigo) {
        log.info("TicketController.isCategorizedAndPrioritizedByIdTicket");
        log.info("TicketController.isCategorizedAndPrioritizedByIdTicket.codigo");
        return ticketService.isCategorizedAndPrioritized(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_TICKET_UPDATE_CATEGORY_AND_PRIORITY, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateTicketEntityIdCategorizedAndIdPriorityAndUserByIdTicket(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                                                                 Integer codigo,
                                                                                         @RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                                                                 Integer codigoCategoria,
                                                                                         @RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                                                                 Integer codigoPrioridad,
                                                                                                @RequestParam Integer codigoUsuarioOperario,
                                                                                                @RequestParam String usuarioMonitor) {
        log.info("TicketController.updateTicketEntityIdCategorizedAndIdPriorityByIdTicket");
        log.info("TicketController.updateTicketEntityIdCategorizedAndIdPriorityByIdTicket.codigo: " + codigo);
        log.info("TicketController.updateTicketEntityIdCategorizedAndIdPriorityByIdTicket.codigoCategoria: " + codigoCategoria);
        log.info("TicketController.updateTicketEntityIdCategorizedAndIdPriorityByIdTicket.codigoPrioridad: " + codigoPrioridad);
        log.info("TicketController.updateTicketEntityIdCategorizedAndIdPriorityByIdTicket.codigoUsuarioOperario: " + codigoUsuarioOperario);
        log.info("TicketController.updateTicketEntityIdCategorizedAndIdPriorityByIdTicket.usuarioMonitor: " + usuarioMonitor);
        return ticketService.updateEntityIdCategoryAndIdPriorityAndUserByIdTicket(
                codigo, codigoCategoria, codigoPrioridad, codigoUsuarioOperario, usuarioMonitor) ? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateTicketById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo,
                                                   @RequestBody TicketUpdateDto ticketUpdateDto) {
        log.info("TicketController.updateTicketById");
        log.info("TicketController.updateTicketById.codigo: " + codigo);
        Integer result = ticketService.updateEntityById(
                ticketMapper.mapTicketUpdateDtoToTicketEntity(ticketUpdateDto, codigo));
        log.info("TicketController.updateTicketById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_ACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> activeTicketById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("TicketController.activeTicketById");
        log.info("TicketController.activeTicketById.codigo: " + codigo);
        Integer result = ticketService.checkActiveById(codigo);
        log.info("TicketController.activeTicketById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_TWO) ?
                        ticketService.activateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactiveTicketById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                   @NotEmpty(message = MSG_EMPTY) Integer codigo) {
        log.info("TicketController.inactiveTicketById");
        log.info("TicketController.inactiveTicketById.codigo: " + codigo);
        Integer result = ticketService.checkActiveById(codigo);
        log.info("TicketController.inactiveTicketById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        ticketService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

}