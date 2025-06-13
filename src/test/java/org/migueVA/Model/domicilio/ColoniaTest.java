package org.migueVA.Model.domicilio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaTest {
    Colonia coloniaManage = Colonia.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Colonia 1 ";
        Integer idMunicipio = 1;

        boolean resultado = coloniaManage.alta(nombre,idMunicipio);
        assertTrue(resultado, "El alta de la Colonia debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = "Cambio Colonia 2 ";
        Integer idMunicipio= 1;

        boolean resultado = coloniaManage.cambio(1,nombreNuevo,idMunicipio);
        assertTrue(resultado, "El cambio  de la Colonia debe ser exitosa");
    }

    @Test
    void baja() {
        boolean resultado = coloniaManage.baja(1);
        assertTrue(resultado, "La baja de la Colonia debe ser exitosa");

    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> coloniaManage.consulta());
    }
}