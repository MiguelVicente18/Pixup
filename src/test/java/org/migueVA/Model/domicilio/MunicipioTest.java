package org.migueVA.Model.domicilio;

import org.junit.jupiter.api.Test;
import org.migueVA.Model.disco.Disco;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioTest {
    Municipio municipioManage = Municipio.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Municipio 2 ";
        Integer idEstado = 4;

        boolean resultado = municipioManage.alta(nombre,idEstado);
        assertTrue(resultado, "El alta del Municipio debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = "Cambio Municipio 2 ";
        Integer idEstado = 2;

        boolean resultado = municipioManage.cambio(1,nombreNuevo,idEstado);
        assertTrue(resultado, "El cambio  del Municipio debe ser exitosa");
    }

    @Test
    void baja() {
        boolean resultado = municipioManage.baja(2);
        assertTrue(resultado, "La baja del municipio debe ser exitosa");

    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> municipioManage.consulta());
    }
}