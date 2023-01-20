package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.gender SET active = ?1 WHERE id_gender = ?2")
    public int updateActiveOfGenderEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.gender WHERE id_gender = ?1")
    public boolean getActiveOfGenderEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.gender SET iso = ?1, gender = ?2 WHERE id_gender = ?3")
    public int updateGenderEntityIsoAndGenderByIdGender(String iso, String gender, Integer idGender);

}