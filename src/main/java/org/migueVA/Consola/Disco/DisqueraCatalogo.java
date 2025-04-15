package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Disquera;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.DisqueraHiberImpl;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class DisqueraCatalogo extends GestorCatalogos<Disquera>
{

    private static DisqueraCatalogo disqueraCatalogo;
    private static final GenericoSQL<Disquera> disqueraSql = DisqueraHiberImpl.getInstance();

    private DisqueraCatalogo()
    {
        super(DisqueraHiberImpl.getInstance());
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
        System.out.print(" *** : Ingrese el nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        disqueraSql.save(disquera);
        return true;
    }

    @Override
    public boolean processEditT(Disquera disquera)
    {

        System.out.print(" *** : Ingrese el nuevo nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );

        disqueraSql.update(disquera);
        return true;
    }

}