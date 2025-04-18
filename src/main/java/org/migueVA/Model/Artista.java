package org.migueVA.Model;

import org.migueVA.Ventana.LecturaAccion;

import java.io.Serializable;

public class Artista extends Catalogo implements Serializable
{
    private String artista;
    public Artista() {
    }

    public Artista(String artista) {
        this.artista = artista;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "artista='" + artista + '\'' +
                ", id=" + id +
                '}';
    }


}
