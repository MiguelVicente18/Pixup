package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.CancionJdbcImplementacion;
import org.migueVA.Jdbc.Implementacion.DiscoJdbcImplementacion;
import org.migueVA.Model.Cancion;
import org.migueVA.Model.Disco;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class CancionCatalogo extends GestorCatalogos<Cancion> {

    private static CancionCatalogo cancionCatalogo;
    private static final GenericoJdbc<Cancion> cancionJdbc = CancionJdbcImplementacion.getInstance();

    private CancionCatalogo()
    {
        super(CancionJdbcImplementacion.getInstance());
    }

    public static CancionCatalogo getInstance()
    {
        if(cancionCatalogo==null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    @Override
    public Cancion newT() {
        return new Cancion();
    }

    @Override
    public boolean processNewT(Cancion cancion) {
        System.out.print(" *** Ingrese el título de la canción: ");
        cancion.setTitulo( ReadUtil.read() );
        System.out.print(" *** Ingrese la duración de la canción en minutos: ");
        cancion.setDuracion( ReadUtil.readFloat() );

        System.out.print(" ***  Ingrese el ID del disco al que pertenece: ");
        Disco disco = DiscoJdbcImplementacion.getInstance().findById( ReadUtil.readInt() );
        if(disco==null) { return false; }
        else { cancion.setDisco( disco ); }

        cancionJdbc.save(cancion);
        return true;
    }

    @Override
    public void edit(Cancion cancion) {
        System.out.print(" ***  Ingrese el ID de la canción a editar: ");
        cancion.setId( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el nuevo título de la canción: ");
        cancion.setTitulo( ReadUtil.read() );

        cancionJdbc.update(cancion);
    }
}
