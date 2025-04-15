package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Colonia;
import org.migueVA.Model.Municipio;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.ColoniaHiberImpl;
import org.migueVA.SqlHibernate.Impl.MunicipioHiberImpl;
import org.migueVA.Util.ReadUtil;

import java.util.List;

public class ColoniaCatalogo extends GestorCatalogos<Colonia>
{
    private static ColoniaCatalogo coloniaCatalogo;
    private static final GenericoSQL<Colonia> coloniaSql = ColoniaHiberImpl.getInstance();

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
        super(ColoniaHiberImpl.getInstance());
    }

    @Override
    public Colonia newT() {
        return new Colonia();
    }
    @Override
    public boolean processNewT(Colonia colonia) {
        System.out.print(" *** : Teclee el nombre de la colonia: ");
        colonia.setColonia( ReadUtil.read() );
        System.out.print("*** : Teclee el código postal de la colonia: ");
        colonia.setCp( ReadUtil.read() );

        MunicipioHiberImpl municipioJdbc = MunicipioHiberImpl.getInstance();
        List<Municipio> list = municipioJdbc.findAll();
        list.forEach(System.out::println);
        System.out.print(" *** : Teclee el ID del municipio al que pertenece: ");

        Municipio municipio = MunicipioHiberImpl.getInstance().findById(ReadUtil.readInt());
        if(municipio==null)
        {
            System.out.println(" ***  No encontrado. *** ");
            return false;
        }
        colonia.setMunicipio(municipio);

        coloniaSql.save(colonia);
        return true;
    }
    @Override
    public boolean processEditT(Colonia colonia) {

        System.out.print(" *** : Ingrese el nuevo nombre de la colonia: ");
        colonia.setColonia( ReadUtil.read() );

        System.out.print(" *** : Ingrese el nuevo código postal de la colonia: ");
        colonia.setCp( ReadUtil.read() );

        coloniaSql.update(colonia);
        return true;
    }
}
