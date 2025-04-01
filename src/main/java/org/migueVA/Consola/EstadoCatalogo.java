package org.migueVA.Consola;

import org.migueVA.Model.Estado;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class EstadoCatalogo extends Catalogos<Estado> {

    public static EstadoCatalogo estadoCatalogo;
    private EstadoCatalogo(){
        super();
    }

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {
            estadoCatalogo = new EstadoCatalogo();
        }
        return estadoCatalogo;
    }

    @Override
    public Estado newT() {
        return new Estado();
    }

    @Override
    public boolean processNewT(Estado estado) {
        System.out.print(" *** Teclee el Nombre del Estado : ");
        estado.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Estado estado) {
        System.out.print(" *** Id del Estado : " + estado.getId());
        System.out.print(" *** Estado en Edición : " + estado.getNombre());
        System.out.print(" *** Teclee el Nuevo Nombre del Estado : ");
        estado.setNombre(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File( "./Estado.object" );
    }
}
