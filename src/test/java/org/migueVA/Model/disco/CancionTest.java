package org.migueVA.Model.disco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancionTest {

    Cancion cancionManage = Cancion.getManage();

    @Test
    void alta() {
        String nombre = "Cancion Prueba 1";
        Double duracion = 3.5;
        Integer idDisco = 3;

        boolean resultado = cancionManage.alta(nombre, duracion, idDisco);
        assertTrue(resultado, "El alta de la cancion debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = "Cancion 1.1";
        Double duracionNueva = 4.0;
        Integer idDiscoNuevo = 3;

        boolean resultado = cancionManage.cambio(3, nombreNuevo, duracionNueva, idDiscoNuevo);
        assertTrue(resultado, "La modificación de la cancion debe ser exitosa");
    }

    @Test
    void baja() {
        boolean resultado = cancionManage.baja(3);
        assertTrue(resultado, "La baja de la cancion debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> cancionManage.consulta());
    }
}
