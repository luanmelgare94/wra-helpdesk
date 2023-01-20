package com.cdc.inlog.pe.controller;

import static com.cdc.inlog.pe.util.Constants.*;
import com.cdc.inlog.pe.dto.parameter.ParameterRegistrationDto;
import com.cdc.inlog.pe.dto.statusticket.StatusTicketDefaultDto;
import com.cdc.inlog.pe.dto.statusticket.StatusTicketResponseByIdDto;
import com.cdc.inlog.pe.dto.typeticket.TypeTicketDefaultDto;
import com.cdc.inlog.pe.dto.typeticket.TypeTicketResponseByIdDto;
import com.cdc.inlog.pe.mapper.*;
import com.cdc.inlog.pe.service.*;
import java.util.List;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(path = API_PARAMETER)
public class ParameterController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private GenderMapper genderMapper;

    @Autowired
    private GenderService genderService;

    @Autowired
    private NationalityMapper nationalityMapper;

    @Autowired
    private NationalityService nationalityService;

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private TypeContractMapper typeContractMapper;

    @Autowired
    private TypeContractService typeContractService;

    @Autowired
    private TypeDocumentMapper typeDocumentMapper;

    @Autowired
    private TypeDocumentService typeDocumentService;

    @Autowired
    private TypeEmailMapper typeEmailMapper;

    @Autowired
    private TypeEmailService typeEmailService;

    @Autowired
    private TypePhoneMapper typePhoneMapper;

    @Autowired
    private TypePhoneService typePhoneService;

    @Autowired
    private TypeTicketMapper typeTicketMapper;

    @Autowired
    private TypeTicketService typeTicketService;

    @Autowired
    private StatusTicketMapper statusTicketMapper;

    @Autowired
    private StatusTicketService statusTicketService;

    @GetMapping(path = SUB_API_FOR_REGISTRATION, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ParameterRegistrationDto> getParameterForRegistration() {
        log.info("ParameterController.getParameterForRegistration");
        return new ResponseEntity<>(ParameterRegistrationDto.builder()
                .generos(genderMapper.mapListGenderEntityToGenderDefaultDto(
                        genderService.getAllEntityActivated()))
                .paises(nationalityMapper.mapListNationalityEntityToNationalityDefaultDto(
                        nationalityService.getAllEntityActivated()))
                .operadoresTelefonicos(operatorMapper.mapListOperatorEntityToOperatorDefaultDto(
                        operatorService.getAllEntityActivated()))
                .tiposContrato(typeContractMapper.mapListTypeContractEntityToTypeContractDefaultDto(
                        typeContractService.getAllEntityActivated()))
                .tiposDocumento(typeDocumentMapper.mapListTypeDocumentEntityToTypeDocumentDefaultDto(
                        typeDocumentService.getAllEntityActivated()))
                .tiposCorreo(typeEmailMapper.mapListTypeEmailEntityToTypeEmailDefaultDto(
                        typeEmailService.getAllEntityActivated()))
                .tiposTelefono(typePhoneMapper.mapListTypePhoneEntityToTypePhoneDefaultDto(
                        typePhoneService.getAllEntityActivated()))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_STATUS_TICKET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StatusTicketDefaultDto>> getAllStatusTicketActivated() {
        log.info("ParameterController.getAllStatusTicketActivated");
        return new ResponseEntity<>(statusTicketMapper.mapListStatusTicketEntityToStatusTicketDefaultDto(
                statusTicketService.getAllEntityActivated()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_STATUS_BY_TICKET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StatusTicketDefaultDto>> getStatusTicketActivatedByIdTicket(@RequestParam
                                                                                           @Min(value = 1,
                                                                                               message = MSG_POSITIVE)
                                                                                                       Integer codigo) {
        log.info("ParameterController.getStatusTicketActivatedByIdTicket");
        log.info("ParameterController.getStatusTicketActivatedByIdTicket.codigo: " + codigo);
        return new ResponseEntity<>(statusTicketMapper.mapListStatusTicketEntityToStatusTicketDefaultDto(
                statusTicketService.getAllStatusTicketActivatedByIdTicket(codigo)), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_STATUS_TICKET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StatusTicketResponseByIdDto> getStatusTicketById(@RequestParam
                                                                               @Min(value = 1, message = MSG_POSITIVE)
                                                                                       Integer codigo) {
        log.info("ParameterController.getStatusTicketById");
        log.info("ParameterController.getStatusTicketById.codigo: " + codigo);
        return statusTicketService.existsEntityById(codigo) ? new ResponseEntity<>(
                statusTicketMapper.mapStatusTicketEntityToStatusTicketResponseByIdDto(
                        statusTicketService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = SUB_API_TYPE_TICKET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<TypeTicketDefaultDto>> getAllTypeTicketActivated() {
        log.info("ParameterController.getAllTypeTicketActivated");
        return new ResponseEntity<>(typeTicketMapper.mapListTypeTicketEntityToTypeTicketDefaultDto(
                typeTicketService.getAllEntityActivated()), HttpStatus.OK);
    }

    @GetMapping(path = SUB_API_TYPE_TICKET_BY_ID, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TypeTicketResponseByIdDto> getTypeTicketById(@RequestParam
                                                                            @Min(value = 1, message = MSG_POSITIVE)
                                                                                   Integer codigo) {
        log.info("ParameterController.getTypeTicketById");
        log.info("ParameterController.getTypeTicketById.codigo: " + codigo);
        return typeTicketService.existsEntityById(codigo) ? new ResponseEntity<>(
                typeTicketMapper.mapTypeTicketEntityTypeToTicketResponseByIdDto(
                        typeTicketService.getAllEntityById(codigo)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}