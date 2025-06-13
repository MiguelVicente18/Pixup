package org.migueVA.Model.domicilio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.migueVA.Model.ClaseCatalogo;
import org.migueVA.Model.disco.Artista;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;
import org.migueVA.baseDatos.impl.CatalogoDaoImpl;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_ESTADO" )
public class Estado extends ClaseCatalogo {
    protected static CatalogoDaoImpl<Estado> catalogoDaoImpl = new CatalogoDaoImpl<>(Estado.class);

    private static Estado manage = null;
    private Estado(){

    }
    public static Estado getManage(){
        if(manage == null){
            manage = new Estado();
        }
        return manage;
    }


    @Override
    public void alta() {
        Estado estado = new Estado();
        estado.setNombre(ReadUtil.read(Funciones.leerNombre));

        catalogoDaoImpl.guardar(estado);
    }

    public boolean alta(String nombre){
        Estado estado = new Estado();
        estado.setNombre(nombre);
        catalogoDaoImpl.guardar(estado);
        return true;
    }

    @Override
    public void cambio() {
        Estado estado = (Estado) buscarCatalogo(catalogoDaoImpl);
        if(estado != null){
            estado.setNombre(ReadUtil.read(Funciones.nuevoNombre));
            catalogoDaoImpl.actualizar(estado);
        }
    }

    public boolean cambio(Integer id, String nuevoNombre) {
        Estado estado = catalogoDaoImpl.findById(id);
        if (estado != null) {
            estado.setNombre(nuevoNombre);
            catalogoDaoImpl.actualizar(estado);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Estado.class);
    }

    public boolean baja(Integer id){
        Estado estado = catalogoDaoImpl.findById(id);
        if(estado!=null){
            catalogoDaoImpl.eliminar(estado);
            return true;
        }
        return false;
    }


    @Override
    public boolean consulta() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return false;
    }
}