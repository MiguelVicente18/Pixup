package org.migueVA.Model;

public class Cancion extends Catalogo  {

    private String titulo;
    private double duracion;
    private Disco disco;

    public Cancion() {
    }

    public Cancion(String titulo, double duracion, Disco disco) {
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

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
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
