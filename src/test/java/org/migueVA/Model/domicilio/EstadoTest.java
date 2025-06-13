package org.migueVA.Model.domicilio;

import org.junit.jupiter.api.Test;
import org.migueVA.Model.disco.Artista;

import static org.junit.jupiter.api.Assertions.*;

class EstadoTest {
    Estado estadoManage = Estado.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Estado 1 ";
        boolean resultado = estadoManage.alta(nombre);
        assertTrue(resultado, "El alta del Estado debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = "Cambio Estado 2";
        boolean resultado = estadoManage.cambio(1, nombreNuevo);
        assertTrue(resultado, "El cambio del Estado debe ser exitosa");

    }

    @Test
    void baja() {
        boolean resultado = estadoManage.baja(1);
        assertTrue(resultado, "La baja del Estado debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> estadoManage.consulta());
    }
}
