package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.CategoryTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryTicketRepository extends JpaRepository<CategoryTicketEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.category_ticket SET active = ?1 WHERE id_category_ticket = ?2")
    public Integer updateActiveOfCategoryTicketEntityByIdCategoryTicket(boolean active, Integer idCategoryTicket);

    @Query(nativeQuery = true, value = "SELECT active FROM public.category_ticket WHERE id_category_ticket = ?1")
    public boolean getActiveOfCategoryTicketEntityByIdCategoryTicket(Integer idCategoryTicket);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.category_ticket SET category_ticket = ?1 WHERE id_category_ticket = ?2")
    public Integer updateCategoryTicketEntityCategoryTicketByIdCategoryTicket(String categoryTicket, Integer idCategoryTicket);

}