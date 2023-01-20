package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.role SET active = ?1 WHERE id_role = ?2")
    public int updateActiveOfRoleEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.role WHERE id_role = ?1")
    public boolean getActiveOfRoleEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.role SET role = ?1, description = ?2 WHERE id_role = ?3")
    public int updateRoleEntityRoleAndDescriptionByIdRole(String role, String description, Integer idRole);

}