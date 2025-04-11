package org.migueVA.Jdbc.Implementacion;

import org.junit.jupiter.api.Test;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Colonia;
import org.migueVA.Model.Municipio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaJdbcImplementacionTest {

    @Test
    void getInstance()
    {
        assertNotNull(ColoniaJdbcImplementacion.getInstance());
    }

    @Test
    void findAll()
    {
        GenericoJdbc<Colonia> coloniaJdbc = ColoniaJdbcImplementacion.getInstance();
        List<Colonia> list = coloniaJdbc.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericoJdbc<Colonia> coloniaJdbc = ColoniaJdbcImplementacion.getInstance();
        Colonia colonia = new Colonia();
        colonia.setNombre("Colonia 3");
        colonia.setCp("23124");

        Municipio municipio = new Municipio();
        municipio.setId(1);
        colonia.setMunicipio(municipio);

        assertTrue(coloniaJdbc.save(colonia));
    }

    @Test
    void update()
    {
        GenericoJdbc<Colonia> coloniaJdbc = ColoniaJdbcImplementacion.getInstance();
        Colonia colonia = new Colonia();
        colonia.setNombre("Colonia3");
        colonia.setId(3);

        assertTrue(coloniaJdbc.update(colonia));
    }

    @Test
    void delete()
    {
        GenericoJdbc<Colonia> coloniaJdbc = ColoniaJdbcImplementacion.getInstance();
        Colonia colonia = new Colonia();
        colonia.setId(1);

        assertTrue(coloniaJdbc.delete(colonia));
    }

    @Test
    void findById()
    {
        GenericoJdbc<Colonia> coloniaJdbc = ColoniaJdbcImplementacion.getInstance();
        Colonia colonia = coloniaJdbc.findById(2);

        assertNotNull(colonia);
        assertEquals(2, colonia.getId());

        System.out.println(colonia.getNombre());
    }
}