package org.migueVA.Model;

import java.io.Serializable;

public class Catalogo implements Serializable {

    protected Integer id;

    public Catalogo()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Catalogo{" +
                "id=" + id +
                '}';
    }
}
