package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.dto.detailticket.DetailTicketAuxiliarRepository;
import com.cdc.inlog.pe.entity.DetailTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DetailTicketRepository extends JpaRepository<DetailTicketEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.detail_ticket SET active = ?1 WHERE id_detail_ticket = ?2")
    public int updateActiveOfDetailTicketEntityByIdDetailTicket(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.detail_ticket WHERE id_detail_ticket = ?1")
    public boolean getActiveOfDetailTicketEntityByIdDetailTicket(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM public.detail_ticket WHERE id_status_ticket = ?1 AND active = ?2")
    public List<DetailTicketEntity> getDetailTicketEntityByIdStatusTicketAndActive(Integer idStatusTicket, boolean active);

    @Query(nativeQuery = true, value = "SELECT * FROM public.detail_ticket WHERE id_ticket = ?1 AND active = ?2")
    public List<DetailTicketEntity> getDetailTicketEntityByIdTicketAndActive(Integer idTicket, boolean active);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.detail_ticket SET active = ?1 WHERE id_ticket = ?2")
    public Integer updateActiveOfDetailTicketEntityByIdTicket(boolean active, Integer idTicket);

    @Query(nativeQuery = true, value = "SELECT MIN(DATE(date_register)) FROM public.detail_ticket WHERE active = ?1 " +
            "GROUP BY DATE(date_register) ORDER BY DATE(date_register)")
    public List<String> getDateRegisterByActive(boolean active);

    @Query(nativeQuery = true, value = "SELECT * FROM public.detail_ticket WHERE active = ?1 ORDER BY " +
            "id_ticket, id_status_ticket, date_register ASC")
    public List<DetailTicketEntity> getDetailTicketEntityByActiveOrderByIdTicketAndIdStatusTicketAndDateRegister(
            boolean active);

    @Query(nativeQuery = true, value = "SELECT DATE(date_register) as fecha, id_ticket as ticket " +
            "FROM public.detail_ticket " +
            "WHERE active = ?1 AND DATE(date_register) BETWEEN '2022-12-01' AND '2023-01-30'" +
            "GROUP BY DATE(date_register), id_ticket ORDER BY DATE(date_register), id_ticket")
    public List<DetailTicketAuxiliarRepository> getDateRegisterWithIdTicketsActivated(boolean active);

    @Query(nativeQuery = true, value = "SELECT DATE(date_register) as fecha " +
            "FROM public.detail_ticket " +
            "WHERE active = ?1 AND DATE(date_register) BETWEEN '2022-12-01' AND '2023-01-30'" +
            "GROUP BY DATE(date_register), id_ticket ORDER BY DATE(date_register), id_ticket")
    public List<String> getListOfDateRegisterWithoutTicket(boolean active);

    @Query(nativeQuery = true, value = "SELECT id_ticket as ticket " +
            "FROM public.detail_ticket " +
            "WHERE active = ?1 AND DATE(date_register) BETWEEN '2022-12-01' AND '2023-01-30'" +
            "GROUP BY DATE(date_register), id_ticket ORDER BY DATE(date_register), id_ticket")
    public List<Integer> getListOfTicketWithoutDateRegister(boolean active);

}