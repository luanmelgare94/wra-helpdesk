package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.UsernameEntity;
import com.cdc.inlog.pe.service.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsernameService extends GenericService<UsernameEntity> {

    public Integer updateUsernameEntityPasswdByIdUsername(String passwd, String username);

    public Page<UsernameEntity> getAllEntity(Pageable pageable);

    public Page<UsernameEntity> getAllEntityActivated(Pageable pageable);

    public Page<UsernameEntity> getAllEntityDeactivated(Pageable pageable);

    public List<UsernameEntity> getAllEntityActivatedByIdRole(Integer idRole);

}