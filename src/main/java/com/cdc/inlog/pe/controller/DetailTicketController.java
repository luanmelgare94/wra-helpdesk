package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.API_DETAIL_TICKET;
import static com.cdc.inlog.pe.util.Constants.APPLICATION_JSON_UTF8_VALUE;
import static com.cdc.inlog.pe.util.Constants.MSG_EMPTY;
import static com.cdc.inlog.pe.util.Constants.MSG_POSITIVE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_ACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_GET_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVATED;
import static com.cdc.inlog.pe.util.Constants.SUB_API_INACTIVE;
import static com.cdc.inlog.pe.util.Constants.SUB_API_PATCH_BY_ID;
import static com.cdc.inlog.pe.util.Constants.SUB_API_REGISTER;
import static com.cdc.inlog.pe.util.Constants.SUB_API_TEST_REPORT;
import static com.cdc.inlog.pe.util.Constants.TIME_ZONE_PERU;

import com.cdc.inlog.pe.dto.detailticket.*;
import com.cdc.inlog.pe.mapper.DetailTicketMapper;
import com.cdc.inlog.pe.service.DetailTicketService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(path = API_DETAIL_TICKET)
public class DetailTicketController {

    @Autowired
    private DetailTicketMapper detailTicketMapper;

    @Autowired
    private DetailTicketService detailTicketService;

    @GetMapping(path = SUB_API_TEST_REPORT, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DetailTicketResponseStatisticsDto>> getReportStatistics() {
        log.info("DetailTicketController.getReportStatistics");
        return new ResponseEntity<>(detailTicketService.getStatistics(), HttpStatus.OK);
    }

    @PostMapping(path = SUB_API_REGISTER, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DetailTicketResponseDto> registerDetailTicket(@RequestBody DetailTicketRequestDto
                                                                                    detailTicketRequestDto) {
        log.info("DetailTicketController.registerDetailTicket");
        return new ResponseEntity<>(detailTicketMapper.mapDetailTicketEntityToDetailTicketResponseDto(
                detailTicketService.registerEntity(
                        detailTicketMapper.mapDetailTicketRequestDtoToDetailTicketEntity(detailTicketRequestDto,
                                LocalDateTime.now(ZoneId.of(TIME_ZONE_PERU))))), HttpStatus.CREATED);
    }

    @GetMapping(path = SUB_API_GET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DetailTicketResponseByIdDto> getDetailTicketById(@RequestParam @Min(value = 1,
            message = MSG_POSITIVE) Integer codigo) {
        log.info("DetailTicketController.getDetailTicketById");
        log.info("DetailTicketController.getDetailTicketById.codigo: " + codigo);
        return detailTicketService.existsEntityById(codigo) ? new ResponseEntity<>(
                detailTicketMapper.mapDetailTicketEntityToDetailTicketResponseByIdDto(
                        detailTicketService.getAllEntityById(codigo)), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = SUB_API_PATCH_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> updateDetailTicketById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                         Integer codigo,
                                                         @RequestBody DetailTicketUpdateDto detailTicketUpdateDto) {
        log.info("DetailTicketController.updateDetailTicketById");
        log.info("DetailTicketController.updateDetailTicketById.codigo: " + codigo);
        Integer result = detailTicketService.updateEntityById(
                detailTicketMapper.mapDetailTicketUpdateDtoToDetailTicketEntity(detailTicketUpdateDto, codigo));
        log.info("DetailTicketController.updateDetailTicketById.result: " + result);
        return result.equals(NUMBER_ONE) ? new ResponseEntity<>(HttpStatus.ACCEPTED) :
                result.equals(NUMBER_TWO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                        new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping(path = SUB_API_INACTIVE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> inactiveDetailTicketById(@RequestParam @Min(value = 1, message = MSG_POSITIVE)
                                                    Integer codigo) {
        log.info("DetailTicketController.inactiveDetailTicketById");
        log.info("DetailTicketController.inactiveDetailTicketById.codigo: " + codigo);
        Integer result = detailTicketService.checkActiveById(codigo);
        log.info("DetailTicketController.inactiveDetailTicketById.result: " + result);
        return result.equals(NUMBER_ZERO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                result.equals(NUMBER_ONE) ?
                        detailTicketService.deactivateEntityById(codigo) ? new ResponseEntity<>(HttpStatus.OK) :
                                new ResponseEntity<>(HttpStatus.RESET_CONTENT)
                        : new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
    }

}