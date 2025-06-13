package org.migueVA.Model.disco;

import jakarta.persistence.*;
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
@Table(name ="TBL_CANCION")

public class Cancion extends ClaseCatalogo
{

    @Column(name = "DURACION")
    private Double duracion;

    @Column(name = "idDisco")
    private Integer idDisco;

    @ManyToOne
    @JoinColumn(name = "TBL_DISCO_ID")
    private Disco disco;

    protected static CatalogoDaoImpl<Cancion> catalogoDaoImpl = new CatalogoDaoImpl<>(Cancion.class);

    private static Cancion manage = null;

    public static Cancion getManage() {
        if (manage == null) {
            manage = new Cancion();
        }
        return manage;
    }

    @Override
    public void alta() {
        Cancion cancion = new Cancion();
        cancion.setNombre(ReadUtil.read(Funciones.leerNombre));
        cancion.setDuracion(Double.valueOf(ReadUtil.read(" --> Duración (en minutos):")));

        System.out.println(" --> Seleccione el Disco al que pertenece:");
        Disco disco = (Disco) buscarCatalogo(Disco.catalogoDaoImpl);
        if (disco != null) cancion.setIdDisco(disco.getId());

        catalogoDaoImpl.guardar(cancion);
    }

    public boolean alta(String nombre, Double duracion, Integer idDisco) {
        Cancion cancion = new Cancion();
        cancion.setNombre(nombre);
        cancion.setDuracion(duracion);
        cancion.setIdDisco(idDisco);
        catalogoDaoImpl.guardar(cancion);
        return true;
    }

    @Override
    public void cambio() {
        Cancion cancion = (Cancion) buscarCatalogo(catalogoDaoImpl);
        if (cancion != null) {
            cancion.setNombre(ReadUtil.read(Funciones.nuevoNombre));
            cancion.setDuracion(Double.valueOf(ReadUtil.read(" --> Nueva duración (en minutos):")));

            System.out.println(" --> Seleccione el nuevo Disco:");
            Disco disco = (Disco) buscarCatalogo(Disco.catalogoDaoImpl);
            if (disco != null) cancion.setIdDisco(disco.getId());

            catalogoDaoImpl.actualizar(cancion);
        }
    }

    public boolean cambio(Integer id, String nombre, Double duracion, Integer idDisco) {
        Cancion cancion = catalogoDaoImpl.findById(id);
        if (cancion != null) {
            cancion.setNombre(nombre);
            cancion.setDuracion(duracion);
            cancion.setIdDisco(idDisco);
            catalogoDaoImpl.actualizar(cancion);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Cancion.class);
    }

    public boolean baja(Integer id) {
        Cancion cancion = catalogoDaoImpl.findById(id);
        if (cancion != null) {
            catalogoDaoImpl.eliminar(cancion);
            return true;
        }
        return false;
    }

    @Override
    public boolean consulta() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return true;
    }
}
