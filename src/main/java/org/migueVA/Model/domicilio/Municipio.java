package org.migueVA.Model.domicilio;


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
@Table( name = "TBL_MUNICIPIO" )
public class Municipio extends ClaseCatalogo {

    @Column( name = "idEstado" )
    private Integer idEstado;

    protected static CatalogoDaoImpl<Municipio> catalogoDaoImpl = new CatalogoDaoImpl<>(Municipio.class);

    private static Municipio manage = null;
    public static Municipio getManage(){
        if(manage == null){
            manage = new Municipio();
        }
        return manage;
    }

    @Override
    public void alta() {
        Municipio municipio = new Municipio();
        municipio.setNombre(ReadUtil.read(Funciones.leerNombre));

        Estado estado = (Estado) buscarCatalogo(Estado.catalogoDaoImpl);
        if(estado != null) municipio.setIdEstado(estado.getId());

        catalogoDaoImpl.guardar(municipio);
    }

    public boolean alta(String nombre, Integer idEstado){
        Municipio municipio = new Municipio();
        municipio.setNombre(nombre);
        municipio.setIdEstado(idEstado);
        catalogoDaoImpl.guardar(municipio);
        return true;
    }

    @Override
    public void cambio() {
        Municipio municipio = (Municipio) buscarCatalogo(catalogoDaoImpl);
        if(municipio != null){
            municipio.setNombre(ReadUtil.read(Funciones.nuevoNombre));

            System.out.println(" --> Nuevo Estado: ");
            Estado estado = (Estado) buscarCatalogo(Estado.catalogoDaoImpl);
            if(estado != null) municipio.setIdEstado(estado.getId());

            catalogoDaoImpl.actualizar(municipio);
        }
    }

    public boolean cambio(Integer id, String nombre, Integer idEstado){
        Municipio municipio = catalogoDaoImpl.findById(id);
        if(municipio!=null){
            municipio.setNombre(nombre);
            municipio.setIdEstado(idEstado);
            catalogoDaoImpl.actualizar(municipio);
            return true;
        }
        return false;
    }

    @Override
    public void baja() {
        realizarBaja(Municipio.class);
    }

    public boolean baja(Integer id){
        Municipio municipio = catalogoDaoImpl.findById(id);
        if(municipio != null){
            catalogoDaoImpl.eliminar(municipio);
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
        return "Municipio{"+
                "Estado = " + Estado.catalogoDaoImpl.findById(idEstado)+
                "}";
    }
}