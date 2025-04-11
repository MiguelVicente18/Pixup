package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.EstadoJdbcImplementacion;
import org.migueVA.Jdbc.Implementacion.MunicipioJdbcImplementacion;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends GestorCatalogos<Municipio>
{
    private static MunicipioCatalogo municipioCatalogo;
    private static final GenericoJdbc<Municipio> municipioJdbc =MunicipioJdbcImplementacion.getInstance();

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
        super(MunicipioJdbcImplementacion.getInstance());
    }

    @Override
    public Municipio newT()
    {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio municipio)
    {
        System.out.print(" *** Teclee el nombre del municipio: ");
        municipio.setNombre( ReadUtil.read() );

        System.out.print(" *** Teclee el ID del estado al que pertenece: ");
        Estado estado = EstadoJdbcImplementacion.getInstance().findById(ReadUtil.readInt());

        if(estado==null)
        {
            return false;
        }
        municipio.setEstado(estado);

        municipioJdbc.save(municipio);
        return true;
    }

    @Override
    public void edit(Municipio municipio)
    {
        System.out.print(" *** Ingrese el ID del municipio a editar: ");
        municipio.setId( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el nuevo nombre del municipio: ");
        municipio.setNombre( ReadUtil.read() );

        municipioJdbc.update(municipio);
    }

}