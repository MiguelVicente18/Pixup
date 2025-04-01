package org.migueVA.Model;

import java.io.Serializable;

public class Colonia extends Catalogo implements Serializable {

    private String nombre;
    private String cp;

    public Colonia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Colonia{" +
                "cp='" + cp + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
