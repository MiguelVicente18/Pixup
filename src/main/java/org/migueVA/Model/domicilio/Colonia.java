package org.migueVA.Model.domicilio;

import jakarta.persistence.Column;
import lombok.*;
import org.migueVA.Model.ClaseCatalogo;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;
import org.migueVA.baseDatos.impl.CatalogoDaoImpl;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table( name = "TBL_COLONIA" )
public class Colonia extends ClaseCatalogo {

    @Column( name = "idMunicipio" )
    private Integer idMunicipio;

    protected static CatalogoDaoImpl<Colonia> catalogoDaoImpl = new CatalogoDaoImpl<>(Colonia.class);

    private static Colonia manage = null;
    public static Colonia getManage(){
        if(manage == null){
            manage = new Colonia();
        }
        return manage;
    }


    @Override
    public void alta() {
        Colonia colonia = new Colonia();
        colonia.setNombre(ReadUtil.read(Funciones.leerNombre));

        Municipio municipio = (Municipio) buscarCatalogo(Municipio.catalogoDaoImpl);
        if(municipio != null) colonia.setIdMunicipio(municipio.getId());

        catalogoDaoImpl.guardar(colonia);
    }

    public boolean alta(String nombre, Integer idMunicipio){
        Colonia colonia = new Colonia();
        colonia.setNombre(nombre);
        colonia.setIdMunicipio(idMunicipio);
        catalogoDaoImpl.guardar(colonia);
        return true;
    }

    @Override
    public void cambio() {
        Colonia colonia = (Colonia) buscarCatalogo(catalogoDaoImpl);
        if(colonia != null){
            colonia.setNombre(ReadUtil.read(Funciones.nuevoNombre));

            System.out.println(" --> Nuevo Municipio: ");
            Municipio municipio = (Municipio) buscarCatalogo(Municipio.catalogoDaoImpl);
            if(municipio != null) colonia.setIdMunicipio(municipio.getId());

            catalogoDaoImpl.actualizar(colonia);
        }
    }


    public boolean cambio(Integer id, String nombre, Integer idMunicipio){
        Colonia colonia = catalogoDaoImpl.findById(id);
        if(colonia!=null){
            colonia.setNombre(nombre);
            colonia.setIdMunicipio(idMunicipio);
            catalogoDaoImpl.actualizar(colonia);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Colonia.class);
    }

    public boolean baja(Integer id){
        Colonia colonia = catalogoDaoImpl.findById(id);
        if(colonia != null){
            catalogoDaoImpl.eliminar(colonia);
            return true;
        }
        return false;
    }

    @Override
    public boolean consulta() {
        System.out.println(realizarVista(catalogoDaoImpl));
        return false;
    }

    @Override
    public String toString(){
        return "Colonia{"+
                "Municipio = " +Municipio.catalogoDaoImpl.findById(idMunicipio)+
                "}";
    }
}