package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Estado;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.EstadoHiberImpl;
import org.migueVA.Util.ReadUtil;

public class EstadoCatalogo extends GestorCatalogos<Estado> {

    private static EstadoCatalogo estadoCatalogo;
    private static final GenericoSQL<Estado> estadoSql = EstadoHiberImpl.getInstance();

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {
            estadoCatalogo = new EstadoCatalogo();
        }
        return estadoCatalogo;
    }

    private EstadoCatalogo( )
    {
        super(EstadoHiberImpl.getInstance());
    }

    @Override
    public Estado newT()
    {
        return new Estado();
    }

    @Override
    public boolean processNewT(Estado estado)
    {
        System.out.print(" *** : Teclee el nombre del estado: ");
        estado.setEstado( ReadUtil.read() );
        estadoSql.save(estado);
        return true;
    }

    @Override
    public boolean  processEditT(Estado estado)
    {
        System.out.print(" *** : Ingrese el nuevo nombre del estado: ");
        estado.setEstado( ReadUtil.read() );
        estadoSql.save(estado);
        return true;
    }

}
