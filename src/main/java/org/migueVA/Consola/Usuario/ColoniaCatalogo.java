package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.ColoniaJdbcImplementacion;
import org.migueVA.Jdbc.Implementacion.MunicipioJdbcImplementacion;
import org.migueVA.Model.Colonia;
import org.migueVA.Model.Municipio;
import org.migueVA.Util.ReadUtil;


public class ColoniaCatalogo extends GestorCatalogos<Colonia>
{
    private static ColoniaCatalogo coloniaCatalogo;
    private static final GenericoJdbc<Colonia> coloniaJdbc = ColoniaJdbcImplementacion.getInstance();

    public static ColoniaCatalogo getInstance( )
    {
        if(coloniaCatalogo==null)
        {
            coloniaCatalogo = new ColoniaCatalogo();
        }
        return coloniaCatalogo;
    }

    private ColoniaCatalogo( )
    {
        super(ColoniaJdbcImplementacion.getInstance());
    }



    @Override
    public Colonia newT() {
        return new Colonia();
    }

    @Override
    public boolean processNewT(Colonia colonia) {
        System.out.print(" ***Teclee el Nombre de la colonia: " );
        colonia.setNombre( ReadUtil.read( ) );
        System.out.println("*** Teclee el Codigo Postal de la colonia: ");
        colonia.setCp(ReadUtil.read());

        Municipio municipio = MunicipioJdbcImplementacion.getInstance().findById(ReadUtil.readInt());

        if(municipio==null) {
            return false; }
        else {
            colonia.setMunicipio(municipio); }
        coloniaJdbc.save(colonia);
        return true;
    }

    @Override
    public void edit(Colonia colonia) {
        System.out.print(" *** Inserte el ID de la Colonia a editar: ");
        colonia.setId( ReadUtil.readInt() );
        System.out.print(" ***  Ingrese el nuevo nombre de la colonia: ");
        colonia.setNombre( ReadUtil.read() );
        System.out.print(" *** Ingrese el nuevo código postal de la colonia: ");
        colonia.setCp( ReadUtil.read() );

        coloniaJdbc.update(colonia);
    }

}
