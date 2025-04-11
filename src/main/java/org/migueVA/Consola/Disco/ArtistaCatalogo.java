package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.ArtistaJdbcImplementacion;
import org.migueVA.Model.Artista;
import org.migueVA.Util.ReadUtil;


public class ArtistaCatalogo extends GestorCatalogos<Artista> {
    private static ArtistaCatalogo artistaCatalogo;
    private static final GenericoJdbc<Artista> artistaJdbc = ArtistaJdbcImplementacion.getInstance();

    private ArtistaCatalogo()
    {
        super(ArtistaJdbcImplementacion.getInstance());
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
        artistaJdbc.save(artista);
        return true;
    }

    @Override
    public void edit(Artista artista)
    {
        System.out.print(" *** Ingrese el ID del estado a editar: ");
        artista.setId( ReadUtil.readInt() );
        System.out.print(" ***  Ingrese el nuevo nombre del estado: ");
        artista.setArtista( ReadUtil.read() );

        artistaJdbc.update(artista);
    }

}
