package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.EstadoJdbcImplementacion;
import org.migueVA.Model.Estado;
import org.migueVA.Util.ReadUtil;

public class EstadoCatalogo extends GestorCatalogos<Estado> {

    private static EstadoCatalogo estadoCatalogo;
    private static final GenericoJdbc<Estado> estadoJdbc = EstadoJdbcImplementacion.getInstance();

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
        super(EstadoJdbcImplementacion.getInstance());
    }

    @Override
    public Estado newT()
    {
        return new Estado();
    }

    @Override
    public boolean processNewT(Estado estado)
    {
        System.out.print(" *** Teclee el nombre del estado: ");
        estado.setNombre( ReadUtil.read() );
        estadoJdbc.save(estado);
        return true;
    }

    @Override
    public void edit(Estado estado)
    {
        System.out.print(" ***  Ingrese el ID del estado a editar: ");
        estado.setId( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el nuevo nombre del estado: ");
        estado.setNombre( ReadUtil.read() );

        estadoJdbc.update(estado);
    }

}
