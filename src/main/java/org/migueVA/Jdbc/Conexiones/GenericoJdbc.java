package org.migueVA.Jdbc.Conexiones;

import java.util.List;

public interface GenericoJdbc<T> {
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
    T findById(Integer id);

}
