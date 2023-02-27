package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.ticket SET active = ?1 WHERE id_ticket = ?2")
    public Integer updateActiveOfTicketEntityByIdTicket(boolean active, Integer idTicket);

    public Page<TicketEntity> findByActive(boolean active, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT active FROM public.ticket WHERE id_ticket = ?1")
    public boolean getActiveOfTicketEntityByIdTicket(Integer idTicket);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.ticket SET description = ?1, " +
            "observation = ?2 WHERE id_ticket = ?3")
    public Integer updateTicketEntityDescriptionAndObservationByIdTicket(String description,
                                                                         String observation,
                                                                         Integer idTicket);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.ticket SET id_category = ?1, " +
            "id_priority = ?2 AND user_last_update = ?3 AND date_last_update = ?4 WHERE id_ticket = ?5")
    public Integer updateTicketEntityIdCategoryAndIdPriorityAndUsernameAndDateLastUpdateByIdTicket(Integer idCategory,
                                                                                                   Integer idPriority,
                                                                                                   String username,
                                                                                                   LocalDateTime now,
                                                                                                   Integer idTicket);
}