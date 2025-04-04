package org.migueVA.Model;

public class Disquera extends Catalogo
{
    private String disquera;

    public Disquera() {
    }

    public String getDisquera() {
        return disquera;
    }

    public void setDisquera(String disquera) {
        this.disquera = disquera;
    }

    @Override
    public String toString() {
        return "Disquera{" +
                "disquera='" + disquera + '\'' +
                ",Id=" + id +
                '}';
    }
}
