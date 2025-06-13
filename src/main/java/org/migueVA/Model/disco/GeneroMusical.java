package org.migueVA.Model.disco;

import jakarta.persistence.Table;
import lombok.*;
import org.migueVA.Model.ClaseCatalogo;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;
import org.migueVA.baseDatos.impl.CatalogoDaoImpl;
import jakarta.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_GENEROMUSICAL" )
public class GeneroMusical extends ClaseCatalogo {
    protected static CatalogoDaoImpl<GeneroMusical> catalogoDaoImpl = new CatalogoDaoImpl<>(GeneroMusical.class);

    private static GeneroMusical manage = null;

    private GeneroMusical() {

    }

    public static GeneroMusical getManage() {
        if (manage == null) {
            manage = new GeneroMusical();
        }
        return manage;
    }


    @Override
    public void alta() {
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre(ReadUtil.read(Funciones.leerNombre));

        catalogoDaoImpl.guardar(generoMusical);
    }

    public boolean alta(String nombre) {
        GeneroMusical generoMusical = new GeneroMusical();
        generoMusical.setNombre(nombre);
        catalogoDaoImpl.guardar(generoMusical);
        return true;
    }

    @Override
    public void cambio() {
        GeneroMusical generoMusical = (GeneroMusical) buscarCatalogo(catalogoDaoImpl);
        if (generoMusical != null) {
            generoMusical.setNombre(ReadUtil.read(Funciones.nuevoNombre));
            catalogoDaoImpl.actualizar(generoMusical);
        }
    }

    public boolean cambio(Integer id, String nombreNuevo) {
        GeneroMusical generoMusical = catalogoDaoImpl.findById(id);
        if (generoMusical != null) {
            generoMusical.setNombre(nombreNuevo);
            catalogoDaoImpl.actualizar(generoMusical);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(GeneroMusical.class);
    }

    public boolean baja(Integer id) {
        GeneroMusical generoMusical = catalogoDaoImpl.findById(id);
        if (generoMusical != null) {
            catalogoDaoImpl.eliminar(generoMusical);
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