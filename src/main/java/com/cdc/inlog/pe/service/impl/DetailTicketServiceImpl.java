package com.cdc.inlog.pe.service.impl;

import com.cdc.inlog.pe.dto.detailticket.DetailTicketAuxiliarRepository;
import com.cdc.inlog.pe.dto.detailticket.DetailTicketAuxiliarResponse;
import com.cdc.inlog.pe.dto.detailticket.DetailTicketResponseStatisticsDto;
import com.cdc.inlog.pe.entity.AuditUsernameEntity;
import com.cdc.inlog.pe.entity.DetailTicketEntity;
import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.repository.AuditUsernameRepository;
import com.cdc.inlog.pe.repository.DetailTicketRepository;
import com.cdc.inlog.pe.repository.UsernameRepository;
import com.cdc.inlog.pe.service.DetailTicketService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static com.cdc.inlog.pe.util.Constants.*;

@Slf4j
@Service
public class DetailTicketServiceImpl implements DetailTicketService {

    @Autowired
    private DetailTicketRepository detailTicketRepository;

    @Autowired
    private UsernameRepository usernameRepository;

    @Autowired
    private AuditUsernameRepository auditUsernameRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<DetailTicketEntity> getAllEntity() {
        log.info("DetailTicketServiceImpl.getAllEntity");
        return detailTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_DETAIL_TICKET));
    }

    @Override
    public List<DetailTicketEntity> getAllEntityActivated() {
        log.info("DetailTicketServiceImpl.getAllEntityActivated");
        return detailTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_DETAIL_TICKET))
                .stream()
                .filter(DetailTicketEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetailTicketEntity> getAllEntityDeactivated() {
        log.info("DetailTicketServiceImpl.getAllEntityDeactivated");
        return detailTicketRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_ID_DETAIL_TICKET))
                .stream()
                .filter(detailTicketEntity -> !detailTicketEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public DetailTicketEntity getAllEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.getAllEntityById");
        log.info("DetailTicketServiceImpl.getAllEntityById.idDetailTicket: " + id);
        return detailTicketRepository.findById(id)
                .orElse(new DetailTicketEntity());
    }

    @Override
    public DetailTicketEntity registerEntity(DetailTicketEntity detailTicketEntity) {
        log.info("DetailTicketServiceImpl.registerEntity");
        DetailTicketEntity detailTicketEntityAux = detailTicketRepository.findById(
                detailTicketRepository.save(detailTicketEntity).getIdDetailTicket()).orElse(new DetailTicketEntity());
        detailTicketEntityAux.setUsernameEntity(usernameRepository.findById(
                detailTicketEntityAux.getUsernameEntity().getIdUsername()).orElse(new UsernameEntity()));
        return detailTicketEntityAux;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.existsEntityById");
        log.info("DetailTicketServiceImpl.existsEntityById.idDetailTicket: " + id);
        return detailTicketRepository.existsById(id);
    }

    @Override
    public Integer updateEntityById(DetailTicketEntity detailTicketEntity) {
        log.info("DetailTicketServiceImpl.updateEntityById");
        log.info("DetailTicketServiceImpl.updateEntityById.idDetailTicket: " + detailTicketEntity.getIdDetailTicket());
        return null;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("DetailTicketServiceImpl.checkActiveById");
        log.info("DetailTicketServiceImpl.checkActiveById.idDetailTicket: " + id);
        if (!detailTicketRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return detailTicketRepository.getActiveOfDetailTicketEntityByIdDetailTicket(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.activateEntityById");
        log.info("DetailTicketServiceImpl.activateEntityById.idDetailTicket: " + id);
        return detailTicketRepository.updateActiveOfDetailTicketEntityByIdDetailTicket(Boolean.TRUE, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("DetailTicketServiceImpl.deactivateEntityById");
        log.info("DetailTicketServiceImpl.deactivateEntityById.idDetailTicket: " + id);
        return detailTicketRepository.updateActiveOfDetailTicketEntityByIdDetailTicket(Boolean.FALSE, id) == NUMBER_ONE;
    }

    @Override
    public List<DetailTicketEntity> getAllDetailTicketEntityActivateByIdStatusTicket(Integer idStatusTicket) {
        log.info("DetailTicketServiceImpl.getAllDetailTicketEntityActivateByIdTypeTicket");
        log.info("DetailTicketServiceImpl.getAllDetailTicketEntityActivateByIdTypeTicket.idStatusTicket" + idStatusTicket);
        return detailTicketRepository.getDetailTicketEntityByIdStatusTicketAndActive(idStatusTicket, Boolean.TRUE);
    }

    @Override
    public List<DetailTicketResponseStatisticsDto> getStatistics() {
        log.info("DetailTicketServiceImpl.getStatistics");
        List<LocalDate> getDates = detailTicketRepository.getDateRegisterByActive(Boolean.TRUE).stream().map(LocalDate::parse).collect(Collectors.toList());
        List<DetailTicketEntity> getDetailTicketByActive = detailTicketRepository.
                getDetailTicketEntityByActiveOrderByIdTicketAndIdStatusTicketAndDateRegister(Boolean.TRUE);
        List<String> getListOfDateRegisterWithoutTicket = detailTicketRepository.getListOfDateRegisterWithoutTicket(Boolean.TRUE);
        List<Integer> getListOfTicketWithoutDateRegister = detailTicketRepository.getListOfTicketWithoutDateRegister(Boolean.TRUE);
        List<DetailTicketAuxiliarResponse> getAverageResponseWaitingTime = getAverageResponseWaitingTime();
        List<AuditUsernameEntity> auditUsernameEntities = auditUsernameRepository.findAll();
        List<DetailTicketAuxiliarRepository> detailTicketAuxiliarRepositories = new ArrayList<>();
        DetailTicketAuxiliarRepository detailTicketAuxiliarRepository;
        int cantidad = getListOfDateRegisterWithoutTicket.size();
        int contador = 0;
        while(cantidad != 0) {
            detailTicketAuxiliarRepository = new DetailTicketAuxiliarRepository();
            detailTicketAuxiliarRepository.setFecha(LocalDate.parse(getListOfDateRegisterWithoutTicket.get(contador)));
            detailTicketAuxiliarRepository.setTicket(getListOfTicketWithoutDateRegister.get(contador));
            detailTicketAuxiliarRepositories.add(detailTicketAuxiliarRepository);
            contador++;
            cantidad--;
        }
        List<DetailTicketResponseStatisticsDto> detailTicketResponseStatisticsDtos = new ArrayList<>();
        DetailTicketResponseStatisticsDto detailTicketResponseStatisticsDto;
        int contador2 = 0;
        for(LocalDate localDate : getDates) {
            List<DetailTicketEntity> getDetailTicketByActiveAux = getDetailTicketByActive
                    .stream()
                    .filter(detailTicketEntity -> detailTicketEntity.getDateRegister().isBefore(localDate.atTime(23, 59, 59)) &&
                            detailTicketEntity.getDateRegister().isAfter(localDate.atStartOfDay()))
                    .collect(Collectors.toList());

            List<DetailTicketAuxiliarRepository> detailTicketAuxiliarRepositoriesAux = detailTicketAuxiliarRepositories
                    .stream()
                    .filter(detailTicketAuxiliarRepositoryAux -> detailTicketAuxiliarRepositoryAux.getFecha().isEqual(localDate))
                    .collect(Collectors.toList());

            detailTicketResponseStatisticsDto = DetailTicketResponseStatisticsDto
                    .builder()
                    .fecha(localDate.toString())
                    .tipoTest(localDate.isAfter(LocalDate.parse("2022-12-30")) ? CAPITAL_LETTER_WORD_POSTEST : CAPITAL_LETTER_WORD_PRETEST)
                    .porcentajeTareasCompletadas(getPercentageOfTasksCompleted(getDetailTicketByActiveAux, detailTicketAuxiliarRepositoriesAux))
                    .porcentajeIncidenciasEscaladas(getPercentageOfEscalatedIncidents(getDetailTicketByActiveAux, detailTicketAuxiliarRepositoriesAux))
                    .tiempoPromedioResolucionIncidentes(getMinutesOfResolutionIncidents(getDetailTicketByActiveAux, detailTicketAuxiliarRepositoriesAux))
                    .tiempoMedioEsperaRespuesta(getAverageResponseWaitingTime.get(contador2).getTiempoEsperaRespuesta())
                    .porcentajeRetencionClientes(getPercentageUserRetention(auditUsernameEntities.get(contador2)))
                    .build();
            detailTicketResponseStatisticsDtos.add(detailTicketResponseStatisticsDto);
            contador2++;
        }
        return detailTicketResponseStatisticsDtos;
    }

    private String getPercentageOfTasksCompleted(List<DetailTicketEntity> getDetailTicketByActiveAux,
                                                 List<DetailTicketAuxiliarRepository> detailTicketAuxiliarRepositoriesAux) {
        int contador = 0;
        int contador2 = 0;
        int contador3 = 0;
        double resultado;
        for (DetailTicketAuxiliarRepository response2 : detailTicketAuxiliarRepositoriesAux) {
            for (DetailTicketEntity response1 : getDetailTicketByActiveAux) {
                if (response2.getTicket().equals(response1.getTicketEntity().getIdTicket())) {
                    if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_THREE)) {
                        break;
                    } else {
                        if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_FIVE) && contador2 != 0) {
                            contador++;
                        } else {
                            if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_FIVE) && contador2 == 0) {
                                contador3++;
                            }
                        }
                    }
                    contador2++;
                }
            }
            contador2 = 0;
        }

        resultado = Math.round(((contador / Double.valueOf(detailTicketAuxiliarRepositoriesAux.size() - contador3)) * 100.0) * 100.0) / 100.0;
        return String.valueOf(resultado) + "%";
    }

    private String getPercentageOfEscalatedIncidents(List<DetailTicketEntity> getDetailTicketByActiveAux,
                                                 List<DetailTicketAuxiliarRepository> detailTicketAuxiliarRepositoriesAux) {
        int contador = 0;
        int contador2 = 0;
        int contador3 = 0;
        int contador4 = 0;
        double resultado;
        for (DetailTicketAuxiliarRepository response2 : detailTicketAuxiliarRepositoriesAux) {
            for (DetailTicketEntity response1 : getDetailTicketByActiveAux) {
                if (response2.getTicket().equals(response1.getTicketEntity().getIdTicket())) {
                    if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_THREE)) {
                        contador++;
                    } else {
                        if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_FOUR)) {
                            contador4++;
                            break;
                        } else {
                            if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_FIVE) && contador2 == 0) {
                                contador3++;
                            }
                        }
                    }
                    contador2++;
                }
            }
            contador2 = 0;
        }

        resultado = Math.round((((contador - contador4 + contador3) / Double.valueOf(detailTicketAuxiliarRepositoriesAux.size() - contador3)) * 100.0) * 100.0) / 100.0;
        return String.valueOf(resultado) + "%";
    }

    private String getMinutesOfResolutionIncidents(List<DetailTicketEntity> getDetailTicketByActiveAux,
                                                 List<DetailTicketAuxiliarRepository> detailTicketAuxiliarRepositoriesAux) {
        int contador = 0;
        int contador2 = 0;
        int contador3 = 0;
        double resultado;
        LocalDateTime fechaInicio = LocalDateTime.now(), fechaFin = LocalDateTime.now();
        long minutos = 0;
        long acumuladorMinutos = 0;
        boolean isExpelled = false;
        for (DetailTicketAuxiliarRepository response2 : detailTicketAuxiliarRepositoriesAux) {
            for (DetailTicketEntity response1 : getDetailTicketByActiveAux) {
                if (response2.getTicket().equals(response1.getTicketEntity().getIdTicket())) {
                    if(response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_ONE)) {
                        fechaInicio = response1.getDateRegister();
                    } else {
                        if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_THREE)) {
                            isExpelled = true;
                            break;
                        } else {
                            if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_FIVE) && contador2 != 0) {
                                contador++;
                                fechaFin = response1.getDateRegister();
                            } else {
                                if (response1.getStatusTicketEntity().getIdStatusTicket().equals(NUMBER_FIVE) && contador2 == 0) {
                                    contador3++;
                                }
                            }
                        }
                        contador2++;
                    }
                }
            }
            if (!isExpelled) {
                minutos = fechaInicio.until(fechaFin, ChronoUnit.MINUTES);
                acumuladorMinutos = acumuladorMinutos + minutos;
            }
            contador2 = 0;
        }
        resultado = Math.round((acumuladorMinutos / Double.valueOf(contador)) * 100.0) / 100.0;
        return String.valueOf(resultado);
    }

    private List<DetailTicketAuxiliarResponse> getAverageResponseWaitingTime() {
        ResponseEntity<DetailTicketAuxiliarResponse[]> response = restTemplate.getForEntity("http://165.227.81.33:5427/indicator", DetailTicketAuxiliarResponse[].class);
        DetailTicketAuxiliarResponse[] detailTicketAuxiliarResponses = response.getBody();
        return new ArrayList<>(Arrays.asList(detailTicketAuxiliarResponses));
    }

    private String getPercentageUserRetention(AuditUsernameEntity auditUsernameEntity) {
        double resultado = Math.round((((Double.valueOf(auditUsernameEntity.getQuantityUsersEndOfPeriod()) -
                Double.valueOf(auditUsernameEntity.getQuantityNewUsers())) /
                Double.valueOf(auditUsernameEntity.getQuantityUsersBeginningPeriod())) * 100.0) * 100.0) / 100.0;
        return String.valueOf(resultado) + "%";
    }

}