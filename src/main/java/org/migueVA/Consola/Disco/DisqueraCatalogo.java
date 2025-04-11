package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.DisqueraJdbcImplementacion;
import org.migueVA.Model.Disquera;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class DisqueraCatalogo extends GestorCatalogos<Disquera>
{

    private static DisqueraCatalogo disqueraCatalogo;
    private static final GenericoJdbc<Disquera> disqueraJdbc = DisqueraJdbcImplementacion.getInstance();

    private DisqueraCatalogo()
    {
        super(DisqueraJdbcImplementacion.getInstance());
    }

    public static DisqueraCatalogo getInstance()
    {
        if(disqueraCatalogo==null)
        {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera)
    {
        System.out.print(" ***  Ingrese el nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        disqueraJdbc.save(disquera);
        return true;
    }

    @Override
    public void edit(Disquera disquera)
    {
        System.out.print(" *** Ingrese el ID de la disquera a editar: ");
        disquera.setId( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el nuevo nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );

        disqueraJdbc.update(disquera);
    }

}