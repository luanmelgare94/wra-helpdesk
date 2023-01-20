package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OperatorRepository extends JpaRepository<OperatorEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.operator SET active = ?1 WHERE id_operator = ?2")
    public int updateActiveOfOperatorEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.operator WHERE id_operator = ?1")
    public boolean getActiveOfOperatorEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.operator SET operator = ?1 WHERE id_operator = ?2")
    public int updateOperatorEntityOperatorByIdOperator(String operator, Integer idOperator);

}