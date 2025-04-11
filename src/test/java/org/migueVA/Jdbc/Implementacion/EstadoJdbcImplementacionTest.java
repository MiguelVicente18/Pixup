package org.migueVA.Jdbc.Implementacion;

import org.junit.jupiter.api.Test;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Estado;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoJdbcImplementacionTest {

    @Test
    void getInstance()
    {
        assertNotNull(EstadoJdbcImplementacion.getInstance());
        //assertNull(EstadoJdbcImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericoJdbc<Estado> estadoJdbc= EstadoJdbcImplementacion.getInstance();
        List<Estado> list = estadoJdbc.findAll();
        assertNotNull( list );
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericoJdbc<Estado> estadoJdbc = EstadoJdbcImplementacion.getInstance();
        Estado estado = new Estado();
        estado.setNombre("CDMX");
        assertTrue(estadoJdbc.save(estado));
    }

    @Test
    void update()
    {
        GenericoJdbc<Estado> estadoJdbc = EstadoJdbcImplementacion.getInstance();
        Estado estado = new Estado();
        estado.setNombre("Ciudad de mejico");
        estado.setId(1);
        assertTrue(estadoJdbc.update(estado));
    }

    @Test
    void delete()
    {
        GenericoJdbc<Estado> estadoJdbc = EstadoJdbcImplementacion.getInstance();
        Estado estado = new Estado();
        estado.setId(1);
        assertTrue(estadoJdbc.delete(estado));
    }

    @Test
    void findById()
    {
        GenericoJdbc<Estado> estadoJdbc = EstadoJdbcImplementacion.getInstance();

        Estado estado = estadoJdbc.findById(4);

        assertNotNull(estado);
        assertEquals(4, estado.getId());

        System.out.println(estado);
    }
}