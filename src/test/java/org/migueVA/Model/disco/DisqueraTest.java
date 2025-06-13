package org.migueVA.Model.disco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisqueraTest {

    Disquera disqueraManage = Disquera.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Disquera 1";
        boolean resultado = disqueraManage.alta(nombre);
        assertTrue(resultado, "El alta de la disquera debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = " Cambio Disquera 2";
        boolean resultado = disqueraManage.cambio(1, nombreNuevo);
        assertTrue(resultado, "La modificación de la disquera debe ser exitosa");

    }

    @Test
    void baja() {
        boolean resultado = disqueraManage.baja(1);
        assertTrue(resultado, "La baja de la disquera debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> disqueraManage.consulta());
    }
}
