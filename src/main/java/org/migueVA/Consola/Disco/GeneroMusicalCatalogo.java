package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.GeneroMusicalJdbcImplementacion;
import org.migueVA.Model.GeneroMusical;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends GestorCatalogos<GeneroMusical>
{
    private static GeneroMusicalCatalogo generoMusicalCatalogo;
    private static final GenericoJdbc<GeneroMusical> generoMusicalJdbc = GeneroMusicalJdbcImplementacion.getInstance();

    private GeneroMusicalCatalogo()
    {
        super(GeneroMusicalJdbcImplementacion.getInstance());
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
        generoMusical.setGeneroMusical( ReadUtil.read() );
        generoMusicalJdbc.save(generoMusical);
        return true;
    }

    @Override
    public void edit(GeneroMusical generoMusical)
    {
        System.out.print(" *** Ingrese el ID del género musical a editar: ");
        generoMusical.setId( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el nuevo nombre del género musical: ");
        generoMusical.setGeneroMusical( ReadUtil.read() );

        generoMusicalJdbc.update(generoMusical);
    }
}