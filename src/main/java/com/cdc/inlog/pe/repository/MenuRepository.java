package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.menu SET active = ?1 WHERE id_menu = ?2")
    public int updateActiveOfMenuEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.menu WHERE id_menu = ?1")
    public boolean getActiveOfMenuEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.menu SET icon = ?1, menu = ?2, url = ?3, " +
            "url = ?4 WHERE id_menu = ?5")
    public int updateMenuEntityIconAndMenuAndUrlAndObservationByIdMenu(String icon,
                                                                       String menu,
                                                                       String url,
                                                                       String observation,
                                                                       Integer idMenu);

    @Query(nativeQuery = true, value = "SELECT m.* " +
            "FROM public.menu_role mr " +
            "INNER JOIN username_role ur ON ur.id_role = mr.id_role " +
            "INNER JOIN menu m ON m.id_menu = mr.id_menu " +
            "INNER JOIN username u ON u.id_username = ur.id_username " +
            "WHERE u.username = ?1")
    public List<Object[]> getMenuByUsername(String username);

}