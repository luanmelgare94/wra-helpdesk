package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TypeTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeTicketRepository extends JpaRepository<TypeTicketEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_ticket SET active = ?1 WHERE id_type_ticket = ?2")
    public Integer updateActiveOfTypeTicketEntityByIdTypeTicket(boolean active, Integer idTypeTicket);

    @Query(nativeQuery = true, value = "SELECT active FROM public.type_ticket WHERE id_type_ticket = ?1")
    public boolean getActiveOfTypeTicketEntityByIdTypeTicket(Integer idTypeTicket);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_ticket SET type_ticket = ?1 WHERE id_type_ticket = ?2")
    public Integer updateTypeTicketEntityTypeTicketByIdTypeTicket(String statusTicket, Integer idTypeTicket);

}