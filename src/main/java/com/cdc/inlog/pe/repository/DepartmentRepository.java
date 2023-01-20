package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.department SET active = ?1 WHERE code_department = ?2")
    public int updateActiveOfDepartmentEntityById(boolean active, String code);

    @Query(nativeQuery = true, value = "SELECT active FROM public.department WHERE code_department = ?1")
    public boolean getActiveOfDepartmentEntityById(String code);

}