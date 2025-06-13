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
@Table( name = "TBL_DISQUERA" )
public class Disquera extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Disquera> catalogoDaoImpl = new CatalogoDaoImpl<>(Disquera.class);

    private static Disquera manage = null;
    private Disquera(){

    }
    public static Disquera getManage(){
        if(manage == null){
            manage = new Disquera();
        }
        return manage;
    }


    @Override
    public void alta() {
        Disquera disquera = new Disquera();
        disquera.setNombre(ReadUtil.read(Funciones.leerNombre));

        catalogoDaoImpl.guardar(disquera);
    }

    public boolean alta(String nombre) {
        Disquera disquera = new Disquera();
        disquera.setNombre(nombre);
        catalogoDaoImpl.guardar(disquera);
        return true;
    }

    @Override
    public void cambio() {
        Disquera disquera = (Disquera) buscarCatalogo(catalogoDaoImpl);
        if(disquera != null){
            disquera.setNombre(ReadUtil.read(Funciones.nuevoNombre));
            catalogoDaoImpl.actualizar(disquera);
        }
    }

    public boolean cambio (Integer id, String nombreNuevo) {
        Disquera disquera = catalogoDaoImpl.findById(id);
        if (disquera != null) {
            disquera.setNombre(nombreNuevo);
            catalogoDaoImpl.actualizar(disquera);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Disquera.class);
    }

    public boolean baja(Integer id) {
        Disquera disquera = catalogoDaoImpl.findById(id);
        if (disquera != null) {
            catalogoDaoImpl.eliminar(disquera);
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