package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.phone SET active = ?1 WHERE id_phone = ?2")
    public int updateActiveOfPhoneEntityById(boolean active, Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.phone SET user_active = ?1 WHERE id_person = ?2")
    public int updateUserActiveOfPhoneEntityByIdPerson(boolean active, Integer idPerson);

    @Query(nativeQuery = true, value = "SELECT active FROM public.phone WHERE id_phone = ?1")
    public boolean getActiveOfPhoneEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.phone SET id_type_phone = ?1, id_operator = ?2, phone = ?3 " +
            "WHERE id_phone = ?4")
    public int updatePhoneEntityIdTypePhoneAndIdOperatorAndPhoneByIdPhone(Integer idTypePhone, Integer idOperator,
                                                                          String phone, Integer idPhone);

    @Query(nativeQuery = true, value = "SELECT * FROM public.phone where id_person = ?1 AND active = ?2")
    public List<PhoneEntity> getAllPhoneEntityByIdPersonAndActive(Integer idPerson, boolean active);

}