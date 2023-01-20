package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.StatusTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusTicketRepository extends JpaRepository<StatusTicketEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.status_ticket SET active = ?1 WHERE id_status_ticket = ?2")
    public Integer updateActiveOfStatusTicketEntityByIdStatusTicket(boolean active, Integer idStatusTicket);

    @Query(nativeQuery = true, value = "SELECT active FROM public.status_ticket WHERE id_status_ticket = ?1")
    public boolean getActiveOfStatusTicketEntityByIdStatusTicket(Integer idStatusTicket);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.status_ticket SET status_ticket = ?1, code_color = ?2 " +
            "WHERE id_status_ticket = ?3")
    public Integer updateStatusTicketEntityStatusTicketAndCodeColorByIdTypePhone(String statusTicket, String codeColor,
                                                                             Integer idStatusTicket);

    @Query(nativeQuery = true, value = "SELECT * FROM public.status_ticket WHERE id_status_ticket NOT IN (SELECT id_status_ticket FROM public.detail_ticket WHERE id_ticket = ?1) and active = true")
    public List<StatusTicketEntity> getStatusTicketByIdTicket(Integer idTicket);

}