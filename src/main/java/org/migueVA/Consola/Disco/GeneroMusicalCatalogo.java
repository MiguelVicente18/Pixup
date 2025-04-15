package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.GeneroMusical;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.GeneroMusicalHiberImpl;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends GestorCatalogos<GeneroMusical>
{
    private static GeneroMusicalCatalogo generoMusicalCatalogo;
    private static final GenericoSQL<GeneroMusical> genericoSQL = GeneroMusicalHiberImpl.getInstance();

    private GeneroMusicalCatalogo()
    {
        super(GeneroMusicalHiberImpl.getInstance());
    }

    public static GeneroMusicalCatalogo getInstance()
    {
        if(generoMusicalCatalogo==null)
        {
            generoMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return generoMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical generoMusical)
    {
        System.out.print(" *** Ingrese el género musical: ");
        generoMusical.setGenero( ReadUtil.read() );
        genericoSQL.save(generoMusical);
        return true;
    }

    @Override
    public boolean processEditT(GeneroMusical generoMusical)
    {
        System.out.print(" *** Ingrese el nuevo nombre del género musical: ");
        generoMusical.setGenero( ReadUtil.read() );

        genericoSQL.update(generoMusical);
        return true;
    }
}