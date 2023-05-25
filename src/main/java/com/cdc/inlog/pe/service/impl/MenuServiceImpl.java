package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.WORD_MENU;
import com.cdc.inlog.pe.entity.MenuEntity;
import com.cdc.inlog.pe.entity.RoleEntity;
import com.cdc.inlog.pe.repository.MenuRepository;
import com.cdc.inlog.pe.repository.RoleRepository;
import com.cdc.inlog.pe.service.MenuService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<MenuEntity> getAllEntity() {
        log.info("MenuServiceImpl.getAllEntity");
        return menuRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_MENU));
    }

    @Override
    public List<MenuEntity> getAllEntityActivated() {
        log.info("MenuServiceImpl.getAllEntityActivated");
        return menuRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_MENU))
                .stream()
                .filter(MenuEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuEntity> getAllEntityDeactivated() {
        log.info("MenuServiceImpl.getAllEntityDeactivated");
        return menuRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_MENU))
                .stream()
                .filter(menuEntity -> !menuEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public MenuEntity getAllEntityById(Integer id) {
        log.info("MenuServiceImpl.getAllEntityById");
        log.info("MenuServiceImpl.getAllEntityById.id: " + id);
        return menuRepository.findById(id)
                .orElse(new MenuEntity());
    }

    @Override
    public MenuEntity registerEntity(MenuEntity menuEntity) {
        log.info("MenuServiceImpl.registerEntity");
        MenuEntity menuEntityAux = menuRepository.save(menuEntity);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        menuEntity.getRoles().forEach(roleEntity1 ->
                roleEntityList.add(roleRepository.findById(roleEntity1.getIdRole()).orElse(new RoleEntity())));
        menuEntityAux.setRoles(roleEntityList);
        return menuEntityAux;
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("MenuServiceImpl.existsEntityById");
        log.info("MenuServiceImpl.existsEntityById.id: " + id);
        return menuRepository.existsById(id);
    }

    @Override
    public Integer updateEntityById(MenuEntity menuEntity) {
        log.info("MenuServiceImpl.existsEntityById");
        log.info("MenuServiceImpl.existsEntityById.id: " + menuEntity.getIdMenu());
        if (menuRepository.existsById(menuEntity.getIdMenu())) {
            MenuEntity menuEntity1 = menuRepository.findById(menuEntity.getIdMenu()).orElse(new MenuEntity());
            menuEntity1.setIcon(menuEntity.getIcon());
            menuEntity1.setMenu(menuEntity.getMenu());
            menuEntity1.setUrl(menuEntity.getUrl());
            menuEntity1.setObservation(menuEntity.getObservation());
            menuEntity1.setRoles(menuEntity.getRoles());
            return !menuRepository.save(menuEntity1).getMenu().isEmpty() ? NUMBER_ONE : NUMBER_ZERO;
        }
        return NUMBER_TWO;
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("MenuServiceImpl.checkActiveById");
        log.info("MenuServiceImpl.checkActiveById.id: " + id);
        if (!menuRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return menuRepository.getActiveOfMenuEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("MenuServiceImpl.activateEntityById");
        log.info("MenuServiceImpl.activateEntityById.id: " + id);
        return menuRepository.updateActiveOfMenuEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("MenuServiceImpl.deactivateEntityById");
        log.info("MenuServiceImpl.deactivateEntityById.id: " + id);
        return menuRepository.updateActiveOfMenuEntityById(false, id) == NUMBER_ONE;
    }

    @Override
    public List<MenuEntity> getAllMenuEntityByUsername(String username) {
        log.info("MenuServiceImpl.getAllMenuEntityByUsername");
        log.info("MenuServiceImpl.getAllMenuEntityByUsername.username: " + username);
        List<MenuEntity> menuEntities = new ArrayList<>();
        menuRepository.getMenuByUsername(username).forEach(objects -> {
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setIdMenu((Integer.parseInt(String.valueOf(objects[0]))));
            menuEntity.setActive(Boolean.parseBoolean(String.valueOf(objects[1])));
            //menuEntity.setDateRegister(LocalDateTime.parse(String.valueOf(objects[2])));
            menuEntity.setIcon(String.valueOf(objects[3]));
            menuEntity.setMenu(String.valueOf(objects[4]));
            menuEntity.setObservation(String.valueOf(objects[5]));
            menuEntity.setUrl(String.valueOf(objects[6]));
            menuEntities.add(menuEntity);
        });
        return menuEntities;
    }
}