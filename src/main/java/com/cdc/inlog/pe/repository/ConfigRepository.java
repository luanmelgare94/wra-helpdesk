package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.ConfigEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ConfigRepository extends JpaRepository<ConfigEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.config SET parameter = ?1, status = ?2, date_update = ?3 WHERE id_config = ?4")
    public int updateConfigEntityByIdConfig(String parameter, Integer status, LocalDateTime dateUpdate, Integer idConfig);

}