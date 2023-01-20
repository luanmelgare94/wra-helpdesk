package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.DetailTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

}