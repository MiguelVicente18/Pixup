package org.migueVA.Model;

public class GeneroMusical extends Catalogo
{
    private String generoMusical;

    public GeneroMusical() {
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    @Override
    public String toString() {
        return "GeneroMusical{" +
                "generoMusical='" + generoMusical + '\'' +
                ", id=" + id +
                '}';
    }
}
