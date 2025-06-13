package org.migueVA.Model.disco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.migueVA.Model.ClaseCatalogo;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;
import org.migueVA.baseDatos.impl.CatalogoDaoImpl;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "TBL_DISCO")
public class Disco extends ClaseCatalogo {

    @Column(name = "precio")
    private Double precio;
    @Column(name = "existencia")
    private Integer existencia;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "fechaLanzamiento")
    private String fechaLanzamiento;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "idArtista")
    private Integer idArtista;
    @Column(name = "idDisquera")
    private Integer idDisquera;
    @Column(name = "idGeneroMusical")
    private Integer idGeneroMusical;

    protected static CatalogoDaoImpl<Disco> catalogoDaoImpl = new CatalogoDaoImpl<>(Disco.class);
    private static Disco manage = null;

    public static Disco getManage() {
        if (manage == null) {
            manage = new Disco();
        }
        return manage;
    }

    @Override
    public void alta() {
        Disco disco = new Disco();
        disco.setNombre(ReadUtil.read(Funciones.leerNombre));
        disco.setPrecio(Double.valueOf(ReadUtil.read(" --> Precio:")));
        disco.setExistencia(Integer.valueOf(ReadUtil.read(" --> Existencia:")));
        disco.setDescuento(Double.valueOf(ReadUtil.read(" --> Descuento (%):")));
        disco.setFechaLanzamiento(ReadUtil.read(" --> Fecha de lanzamiento (dd/mm/aaaa):"));
        disco.setImagen(ReadUtil.read(" --> Ruta de la imagen:"));

        Artista artista = (Artista) buscarCatalogo(Artista.catalogoDaoImpl);
        if (artista != null) disco.setIdArtista(artista.getId());

        Disquera disquera = (Disquera) buscarCatalogo(Disquera.catalogoDaoImpl);
        if (disquera != null) disco.setIdDisquera(disquera.getId());

        GeneroMusical genero = (GeneroMusical) buscarCatalogo(GeneroMusical.catalogoDaoImpl);
        if (genero != null) disco.setIdGeneroMusical(genero.getId());

        catalogoDaoImpl.guardar(disco);
    }

    public boolean alta(String nombre, Double precio, Integer existencia, Double descuento, String fechaLanzamiento, String imagen, Integer idArtista, Integer idDisquera, Integer idGeneroMusical) {
        Disco disco = new Disco();
        disco.setNombre(nombre);
        disco.setPrecio(precio);
        disco.setExistencia(existencia);
        disco.setDescuento(descuento);
        disco.setFechaLanzamiento(fechaLanzamiento);
        disco.setImagen(imagen);
        disco.setIdArtista(idArtista);
        disco.setIdDisquera(idDisquera);
        disco.setIdGeneroMusical(idGeneroMusical);
        catalogoDaoImpl.guardar(disco);
        return true;
    }

    @Override
    public void cambio() {
        Disco disco = (Disco) buscarCatalogo(catalogoDaoImpl);
        if (disco != null) {
            disco.setNombre(ReadUtil.read(Funciones.nuevoNombre));
            disco.setPrecio(Double.valueOf(ReadUtil.read(" --> Nuevo precio:")));
            disco.setExistencia(Integer.valueOf(ReadUtil.read(" --> Nueva existencia:")));
            disco.setDescuento(Double.valueOf(ReadUtil.read(" --> Nuevo descuento (%):")));
            disco.setFechaLanzamiento(ReadUtil.read(" --> Nueva fecha de lanzamiento (dd/mm/aaaa):"));
            disco.setImagen(ReadUtil.read(" --> Nueva ruta de la imagen:"));

            System.out.println("Nuevo Artista:");
            Artista artista = (Artista) buscarCatalogo(Artista.catalogoDaoImpl);
            if (artista != null) disco.setIdArtista(artista.getId());

            System.out.println("Nueva Disquera:");
            Disquera disquera = (Disquera) buscarCatalogo(Disquera.catalogoDaoImpl);
            if (disquera != null) disco.setIdDisquera(disquera.getId());

            System.out.println("Nuevo Género Musical:");
            GeneroMusical genero = (GeneroMusical) buscarCatalogo(GeneroMusical.catalogoDaoImpl);
            if (genero != null) disco.setIdGeneroMusical(genero.getId());

            catalogoDaoImpl.actualizar(disco);
        }
    }

    public boolean cambio(Integer id, String nombre, Double precio, Integer existencia, Double descuento, String fechaLanzamiento, String imagen, Integer idArtista, Integer idDisquera, Integer idGeneroMusical) {
        Disco disco = catalogoDaoImpl.findById(id);
        if (disco != null) {
            disco.setNombre(nombre);
            disco.setPrecio(precio);
            disco.setExistencia(existencia);
            disco.setDescuento(descuento);
            disco.setFechaLanzamiento(fechaLanzamiento);
            disco.setImagen(imagen);
            disco.setIdArtista(idArtista);
            disco.setIdDisquera(idDisquera);
            disco.setIdGeneroMusical(idGeneroMusical);
            catalogoDaoImpl.actualizar(disco);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Disco.class);
    }

    public boolean baja(Integer id) {
        Disco disco = catalogoDaoImpl.findById(id);
        if (disco != null) {
            catalogoDaoImpl.eliminar(disco);
            return true;
        }
        return false;
    }

    @Override
    public boolean consulta() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return true;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "Precio=" + precio +
                ", Existencia=" + existencia +
                ", Descuento=" + descuento +
                ", FechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", Imagen='" + imagen + '\'' +
                ", Artista=" + Artista.catalogoDaoImpl.findById(idArtista) +
                ", Disquera=" + Disquera.catalogoDaoImpl.findById(idDisquera) +
                ", GeneroMusical=" + GeneroMusical.catalogoDaoImpl.findById(idGeneroMusical) +
                '}';
    }
}