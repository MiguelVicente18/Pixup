package org.migueVA.SqlHibernate;

import java.util.List;

public interface GenericoSQL<T> {

    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
    T findById( Integer id );

}
