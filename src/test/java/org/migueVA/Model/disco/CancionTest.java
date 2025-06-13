package org.migueVA.Model.disco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancionTest {

    Cancion cancionManage = Cancion.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Cancion 1";
        Double duracion = 3.5;
        Integer idDisco = 1;

        boolean resultado = cancionManage.alta(nombre, duracion, idDisco);
        assertTrue(resultado, "El alta de la cancion debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = "Cambio Cancion 2";
        Double duracionNueva = 4.0;
        Integer idDiscoNuevo = 1;

        boolean resultado = cancionManage.cambio(1, nombreNuevo, duracionNueva, idDiscoNuevo);
        assertTrue(resultado, "La modificación de la cancion debe ser exitosa");
    }

    @Test
    void baja() {
        boolean resultado = cancionManage.baja(1);
        assertTrue(resultado, "La baja de la cancion debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> cancionManage.consulta());
    }
}
