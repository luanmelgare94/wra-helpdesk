package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TypeEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeEmailRepository extends JpaRepository<TypeEmailEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_email SET active = ?1 WHERE id_type_email = ?2")
    public int updateActiveOfTypeEmailEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.type_email WHERE id_type_email = ?1")
    public boolean getActiveOfTypeEmailEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_email SET typeEmail = ?1 WHERE id_type_email = ?2")
    public int updateTypeEmailEntityTypeEmailByIdTypeEmail(String typeEmail, Integer idTypeEmail);

}