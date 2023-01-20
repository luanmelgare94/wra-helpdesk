package com.cdc.inlog.pe.service.generic;

import java.util.List;

public interface GenericService<T> {

    public List<T> getAllEntity();

    public List<T> getAllEntityActivated();

    public List<T> getAllEntityDeactivated();

    public T getAllEntityById(Integer id);

    public T registerEntity(T t);

    public boolean existsEntityById(Integer id);

    public Integer updateEntityById(T t);

    public Integer checkActiveById(Integer id);

    public boolean activateEntityById(Integer id);

    public boolean deactivateEntityById(Integer id);

}