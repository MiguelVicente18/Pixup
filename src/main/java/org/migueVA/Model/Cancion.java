package org.migueVA.Model;

import java.io.Serializable;

public class Cancion extends Catalogo implements Serializable {

    private String titulo;
    private float duracion;
    private Disco disco;

    public Cancion() {
    }

    public Cancion(String titulo, float duracion, Disco disco) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.disco = disco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", disco=" + disco.getTituloDisco() +
                ", id=" + id +
                '}';
    }
}
