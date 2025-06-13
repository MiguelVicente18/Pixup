package org.migueVA.Model.disco;

import lombok.*;
import org.migueVA.Model.ClaseCatalogo;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;
import org.migueVA.baseDatos.impl.CatalogoDaoImpl;
import jakarta.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name ="TBL_ARTISTA")

public class Artista extends ClaseCatalogo
{
    protected static CatalogoDaoImpl<Artista> catalogoDaoImpl = new CatalogoDaoImpl<>(Artista.class);

    private static Artista manage = null;
    private Artista(){

    }
    public static Artista getManage(){
        if(manage == null){
            manage = new Artista();
        }
        return manage;
    }

    @Override
    public void alta() {
        Artista artista = new Artista();
        artista.setNombre(ReadUtil.read(Funciones.leerNombre));

        catalogoDaoImpl.guardar(artista);
    }

    public boolean alta(String nombre){
        Artista artista = new Artista();
        artista.setNombre(nombre);
        catalogoDaoImpl.guardar(artista);
        return true;
    }

    @Override
    public void cambio() {
        Artista artista = (Artista) buscarCatalogo(catalogoDaoImpl);
        if(artista!=null){
            artista.setNombre(ReadUtil.read(Funciones.nuevoNombre));
            catalogoDaoImpl.actualizar(artista);
        }
    }

    public boolean cambio(Integer id, String nuevoNombre) {
        Artista artista = catalogoDaoImpl.findById(id);
        if (artista != null) {
            artista.setNombre(nuevoNombre);
            catalogoDaoImpl.actualizar(artista);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Artista.class);
    }
    public boolean baja(Integer id){
        Artista artista = catalogoDaoImpl.findById(id);
        if(artista!=null){
            catalogoDaoImpl.eliminar(artista);
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
