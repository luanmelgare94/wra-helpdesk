package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.district SET active = ?1 WHERE id_district = ?2")
    public int updateActiveOfProvinceEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.district WHERE id_district = ?1")
    public boolean getActiveOfProvinceEntityById(Integer id);

}