package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.UsernameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsernameRepository extends JpaRepository<UsernameEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.username SET active = ?1 WHERE id_username = ?2")
    public Integer updateActiveOfUsernameEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.username WHERE id_username = ?1")
    public boolean getActiveOfUsernameEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.username SET passwd = ?1 WHERE username = ?2")
    public Integer updateUsernameEntityPasswdByIdUsername(String passwd, String username);

    @Query(nativeQuery = true, value = "SELECT id_person FROM public.username WHERE id_username = ?1")
    public Integer getIdPersonOfUsernameEntityByIdUsername(Integer idUsername);

    public boolean existsUsernameEntityByUsername(String username);

    public Page<UsernameEntity> findByActive(boolean active, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM public.username us INNER JOIN public.username_role ur " +
            " ON us.id_username = ur.id_username WHERE us.active = ?1 AND ur.id_role = ?2")
    public List<UsernameEntity> getUsernameEntityByActiveAndRole(boolean active, Integer idRole);

}