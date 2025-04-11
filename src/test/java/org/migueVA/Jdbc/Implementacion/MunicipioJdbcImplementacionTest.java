package org.migueVA.Jdbc.Implementacion;

import org.junit.jupiter.api.Test;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioJdbcImplementacionTest {

    @Test
    void getInstance()
    {
        assertNotNull(MunicipioJdbcImplementacion.getInstance());
        //assertNull(EstadoJdbcImpl.getInstance());
    }

    @Test
    void findAll()
    {
        GenericoJdbc<Municipio> municipioJdbc = MunicipioJdbcImplementacion.getInstance();
        List<Municipio> list = municipioJdbc.findAll();
        assertNotNull( list );
        assertFalse(list.isEmpty());
        list.stream().forEach(System.out::println);
    }

    @Test
    void save()
    {
        GenericoJdbc<Municipio> municipioJdbc = MunicipioJdbcImplementacion.getInstance();
        Municipio municipio = new Municipio();
        municipio.setNombre("Reynosa");

        Estado estado = new Estado();
        estado.setId(1);
        municipio.setEstado(estado);
        assertTrue(municipioJdbc.save(municipio));
    }

    @Test
    void update()
    {
        GenericoJdbc<Municipio> municipioJdbc = MunicipioJdbcImplementacion.getInstance();
        Municipio municipio = new Municipio();
        municipio.setNombre("Reinosa");
        municipio.setId(5);
        assertTrue(municipioJdbc.update(municipio));
    }

    @Test
    void delete()
    {
        GenericoJdbc<Municipio> municipioJdbc = MunicipioJdbcImplementacion.getInstance();
        Municipio municipio = new Municipio();
        municipio.setId(5);
        assertTrue(municipioJdbc.delete(municipio));
    }

    @Test
    void findById()
    {
        GenericoJdbc<Municipio> municipioGenericJdbc = MunicipioJdbcImplementacion.getInstance();

        Municipio municipio = municipioGenericJdbc.findById(4);

        assertNotNull(municipio);
        assertEquals(4, municipio.getId());

        System.out.println(municipio.getNombre());
    }
}