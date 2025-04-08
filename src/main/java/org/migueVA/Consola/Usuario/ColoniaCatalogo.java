package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.ColoniaJdbcImplementacion;
import org.migueVA.Model.Colonia;
import org.migueVA.Model.Municipio;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class ColoniaCatalogo extends GestorCatalogos<Colonia> {

    private static ColoniaCatalogo coloniaCatalogo;
    private MunicipioCatalogo municipioCatalogo;

    private ColoniaCatalogo(){

        super();
        municipioCatalogo = MunicipioCatalogo.getInstance();
    }

    public static ColoniaCatalogo getInstance(){
        if(coloniaCatalogo == null){
            coloniaCatalogo = new ColoniaCatalogo();
        }
        return coloniaCatalogo;
    }

    @Override
    public Colonia newT() {
        return new Colonia();
    }

    @Override
    public boolean processNewT(Colonia colonia) {
        System.out.print(" ***Teclee el nombre de la colonia: " );
        colonia.setNombre( ReadUtil.read( ) );
        System.out.println("*** Teclee el cp de la colonia: ");
        colonia.setCp(ReadUtil.read());

        Municipio municipio = municipioCatalogo.getMunicipioById();

        if(municipio==null) {
            return false; }
        else {
            colonia.setMunicipio(municipio); }

        return true;
    }

    @Override
    public void processEditT(Colonia colonia) {
        System.out.print("*** ID de la colonia: " + colonia.getId( ) );
        System.out.print("*** Colonia en edición: " + colonia.getNombre( ) );
        System.out.print("*** Teclee el nuevo nombre de la colonia: " );
        colonia.setNombre( ReadUtil.read( ) );
        System.out.print("*** Teclee el nuevo Codigo Postal de la colonia: ");
        colonia.setCp( ReadUtil.read( ) );

        Municipio municipio = municipioCatalogo.getMunicipioById();

        if(municipio==null)
        {
            System.out.println(" *** Estado no encontrado. No se pudo actualizar el estado del municipio, compruébelo e inténtelo de nuevo *** ");
        }
        else
        {
            colonia.setMunicipio(municipio);
        }

    }

    @Override
    public File getFile() {
        return new File ("./src/main/fileStorage/Colonias.list" );
    }

    @Override
    public void print() {
        GenericoJdbc<Colonia> coloniaJdbc = new ColoniaJdbcImplementacion();
        coloniaJdbc.findAll().stream().forEach(System.out::println);
    }


}
