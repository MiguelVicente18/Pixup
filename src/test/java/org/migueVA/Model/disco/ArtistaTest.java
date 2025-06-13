package org.migueVA.Model.disco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaTest {
    Artista artistaManage = Artista.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Artista 1";
        boolean resultado = artistaManage.alta(nombre);
        assertTrue(resultado, "El alta del artista debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = " Cambio Artista 2";
        boolean resultado = artistaManage.cambio(1, nombreNuevo);
        assertTrue(resultado, "La modificación del artista debe ser exitosa");

    }

    @Test
    void baja() {
        boolean resultado = artistaManage.baja(1);
        assertTrue(resultado, "La baja del artista debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> artistaManage.consulta());
    }
}