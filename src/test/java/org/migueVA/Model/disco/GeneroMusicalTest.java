package org.migueVA.Model.disco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneroMusicalTest {

    GeneroMusical generoMusicalManage = GeneroMusical.getManage();

    @Test
    void alta() {
        String nombre = " Prueba GeneroMusical Prueba 1";
        boolean resultado = generoMusicalManage.alta(nombre);
        assertTrue(resultado, "El alta del generoMusical debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = " Cambio GeneroMusical 2";
        boolean resultado = generoMusicalManage.cambio(1, nombreNuevo);
        assertTrue(resultado, "La modificación del generoMusical debe ser exitosa");
    }

    @Test
    void baja() {
        boolean resultado = generoMusicalManage.baja(1);
        assertTrue(resultado, "La baja del generoMusical debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> generoMusicalManage.consulta());
    }
}
