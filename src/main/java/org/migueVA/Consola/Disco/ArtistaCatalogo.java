package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Artista;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.ArtistaHiberImpl;
import org.migueVA.Util.ReadUtil;


public class ArtistaCatalogo extends GestorCatalogos<Artista> {
    private static ArtistaCatalogo artistaCatalogo;
    private static final GenericoSQL<Artista> artistaSql = ArtistaHiberImpl.getInstance();

    private ArtistaCatalogo()
    {
        super(ArtistaHiberImpl.getInstance());
    }

    public static ArtistaCatalogo getInstance()
    {
        if(artistaCatalogo==null)
        {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT()
    {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista)
    {
        System.out.print(" *** Ingrese el nombre del artista: ");
        artista.setArtista( ReadUtil.read() );
        artistaSql.save(artista);
        return true;
    }

    @Override
    public boolean  processEditT(Artista artista)
    {
        System.out.print("> Ingrese el nuevo nombre del artista: ");
        artista.setArtista( ReadUtil.read() );

        artistaSql.update(artista);
        return true;
    }

}
