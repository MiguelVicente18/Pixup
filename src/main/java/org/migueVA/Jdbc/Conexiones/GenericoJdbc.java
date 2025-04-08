package org.migueVA.Jdbc.Conexiones;

import java.util.List;

public interface GenericoJdbc<T> {
    List<T> findAll();
}
