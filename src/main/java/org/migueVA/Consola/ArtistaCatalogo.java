package org.migueVA.Consola;

import org.migueVA.Model.Artista;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class ArtistaCatalogo extends Catalogos<Artista> {

    private static ArtistaCatalogo artistaCatalogo;

    private ArtistaCatalogo() {
    }

    public static ArtistaCatalogo getInstance()
    {
        if(artistaCatalogo == null)
        {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT() {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista) {
        System.out.print(" *** Escriba el Nombre del Artista : ");
        artista.setArtista(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Artista artista) {
        System.out.print(" *** El nombre del Artista es : " + artista.getArtista());
        System.out.print(" *** Escribe el Nombre del Nuevo Valor del  Artista : ");
        artista.setArtista(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File( "./Artista.object");
    }
}
