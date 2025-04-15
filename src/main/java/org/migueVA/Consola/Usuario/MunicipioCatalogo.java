package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.EstadoHiberImpl;
import org.migueVA.SqlHibernate.Impl.MunicipioHiberImpl;
import org.migueVA.Util.ReadUtil;

import java.io.File;
import java.util.List;

public class MunicipioCatalogo extends GestorCatalogos<Municipio>
{
    private static MunicipioCatalogo municipioCatalogo;
    private static final GenericoSQL<Municipio> municipioSql = MunicipioHiberImpl.getInstance();

    public static MunicipioCatalogo getInstance( )
    {
        if(municipioCatalogo==null)
        {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    private MunicipioCatalogo( )
    {
        super(MunicipioHiberImpl.getInstance());
    }

    @Override
    public Municipio newT()
    {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio municipio)
    {
        System.out.print(" *** : Teclee el nombre del municipio: ");
        municipio.setMunicipio( ReadUtil.read() );

        EstadoHiberImpl estadoSql = EstadoHiberImpl.getInstance();
        List<Estado> list = estadoSql.findAll();
        list.forEach(System.out::println);
        System.out.print(" *** :  Teclee el ID del estado al que pertenece: ");

        Estado estado = EstadoHiberImpl.getInstance().findById(ReadUtil.readInt());
        if(estado==null)
        {
            System.out.println(" ***  No encontrado. *** ");
            return false;
        }
        municipio.setEstado(estado);

        municipioSql.save(municipio);
        return true;
    }

    @Override
    public boolean processEditT (Municipio municipio)
    {
        System.out.print(" *** : Ingrese el nuevo nombre del municipio: ");
        municipio.setMunicipio( ReadUtil.read() );

        municipioSql.update(municipio);
        return true;
    }

}