package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TypePhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypePhoneRepository extends JpaRepository<TypePhoneEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_phone SET active = ?1 WHERE id_type_phone = ?2")
    public int updateActiveOfTypePhoneEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.type_phone WHERE id_type_phone = ?1")
    public boolean getActiveOfTypePhoneEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_phone SET typePhone = ?1 WHERE id_type_phone = ?2")
    public int updateTypePhoneEntityTypePhoneByIdTypePhone(String typePhone, Integer idTypePhone);

}