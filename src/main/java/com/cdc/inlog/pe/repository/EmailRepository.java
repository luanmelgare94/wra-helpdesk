package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.email SET active = ?1 WHERE id_email = ?2")
    public int updateActiveOfEmailEntityById(boolean active, Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.email SET user_active = ?1 WHERE id_person = ?2")
    public int updateUserActiveOfEmailEntityByIdPerson(boolean userActive, Integer idPerson);

    @Query(nativeQuery = true, value = "SELECT active FROM public.email WHERE id_email = ?1")
    public boolean getActiveOfEmailEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.email SET id_type_email = ?1, email = ?2 WHERE id_email = ?3")
    public int updateEmailEntityIdTypeEmailAndEmailByIdEmail(Integer idTypeEmail, String email, Integer idEmail);

    @Query(nativeQuery = true, value = "SELECT * FROM public.email where id_person = ?1 AND active = ?2")
    public List<EmailEntity> getAllEmailEntityByIdPersonAndActive(Integer idPerson, boolean active);

}