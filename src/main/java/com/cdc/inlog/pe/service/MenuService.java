package com.cdc.inlog.pe.service;

import com.cdc.inlog.pe.entity.MenuEntity;
import com.cdc.inlog.pe.service.generic.GenericService;

import java.util.List;

public interface MenuService extends GenericService<MenuEntity> {

    public List<MenuEntity> getAllMenuEntityByUsername(String username);

}