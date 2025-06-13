package org.migueVA.Model.disco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscoTest {

    Disco discoManage = Disco.getManage();

    @Test
    void alta() {
        String nombre = "Prueba Disco 1 ";
        Double precio = 25.54;
        Integer existencia = 80;
        Double descuento = 0.2;
        String fechaLanzamiento = "01/01/2025";
        String imagen = "ruta/imagen.jpg";
        Integer idArtista = 1;
        Integer idDisquera = 1;
        Integer idGeneroMusical = 1;

        boolean resultado = discoManage.alta(nombre, precio, existencia, descuento, fechaLanzamiento, imagen, idArtista, idDisquera, idGeneroMusical);
        assertTrue(resultado, "El alta del disco debe ser exitosa");
    }

    @Test
    void cambio() {
        String nombreNuevo = " Cambio Disco 2";
        Double precioNuevo = 24.99;
        Integer existenciaNueva = 50;
        Double descuentoNuevo = 0.4;
        String fechaLanzamientoNueva = "01/02/2023";
        String imagenNueva = "nueva/ruta/imagen.jpg";
        Integer idArtistaNuevo = 1;
        Integer idDisqueraNuevo = 1;
        Integer idGeneroMusicalNuevo = 1;

        boolean resultado = discoManage.cambio(1, nombreNuevo, precioNuevo, existenciaNueva, descuentoNuevo, fechaLanzamientoNueva, imagenNueva, idArtistaNuevo, idDisqueraNuevo, idGeneroMusicalNuevo);
        assertTrue(resultado, "La modificación del disco debe ser exitosa");
    }

    @Test
    void baja() {
        boolean resultado = discoManage.baja(1);
        assertTrue(resultado, "La baja del disco debe ser exitosa");
    }

    @Test
    void consulta() {
        assertDoesNotThrow(() -> discoManage.consulta());
    }
}
