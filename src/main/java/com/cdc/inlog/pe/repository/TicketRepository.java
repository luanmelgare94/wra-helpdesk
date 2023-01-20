package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.ticket SET active = ?1 WHERE id_ticket = ?2")
    public Integer updateActiveOfTicketEntityByIdTicket(boolean active, Integer idTicket);

    public Page<TicketEntity> findByActive(boolean active, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT active FROM public.ticket WHERE id_ticket = ?1")
    public boolean getActiveOfTicketEntityByIdTicket(Integer idTicket);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.ticket SET id_type_ticket = ?1, description = ?2, " +
            "observation = ?3 WHERE id_ticket = ?4")
    public Integer updateTicketEntityIdTypeTicketAndDescriptionAndObservationByIdTicket(Integer idTypeTicket,
                                                                                    String description,
                                                                                    String observation,
                                                                                    Integer idTicket);

}