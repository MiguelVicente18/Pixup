package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.ArtistaJdbcImplementacion;
import org.migueVA.Model.Artista;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class ArtistaCatalogo extends GestorCatalogos<Artista> {

    private static ArtistaCatalogo artistaCatalogo;

    private ArtistaCatalogo() {
        super();
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
        return new File( "./src/main/fileStorage/Artistas.object");
    }

    @Override
    public void print() {
        GenericoJdbc<Artista> artistaJdbc = new ArtistaJdbcImplementacion();
        artistaJdbc.findAll().stream().forEach(System.out::println);
    }

    public Artista getArtistaById() {
        if (isListEmpty()) {
            System.out.println("> No hay artistas registrados.");
            return null;
        }
        while (true) {
            System.out.print("> Ingrese el ID del artista: ");
            int id = ReadUtil.readInt();
            Artista artista = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (artista != null) {
                return artista;
            }
            System.out.println("> ID incorrecto, inténtelo nuevamente.");
        }
}
}
