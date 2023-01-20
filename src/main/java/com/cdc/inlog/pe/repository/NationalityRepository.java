package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.NationalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NationalityRepository extends JpaRepository<NationalityEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.nationality SET active = ?1 WHERE id_nationality = ?2")
    public int updateActiveOfNationalityEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.nationality WHERE id_nationality = ?1")
    public boolean getActiveOfNationalityEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.nationality SET iso = ?1, country = ?2 WHERE id_nationality = ?3")
    public int updateNationalityEntityIsoAndCountryByIdNationality(String iso, String country, Integer idNationality);

}