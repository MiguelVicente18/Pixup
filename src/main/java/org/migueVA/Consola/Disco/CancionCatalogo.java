package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.CancionJdbcImplementacion;
import org.migueVA.Model.Cancion;
import org.migueVA.Model.Disco;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class CancionCatalogo extends GestorCatalogos<Cancion> {
    private static CancionCatalogo cancionCatalogo;

    private CancionCatalogo() {
        super();
    }

    public static CancionCatalogo getInstance()
    {
        if(cancionCatalogo == null)
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
        System.out.print(" *** Escriba el nombre de la cancion: ");
        cancion.setTitulo(ReadUtil.read());

        System.out.print(" *** Escriba la duracion de la cancion: ");
        cancion.setDuracion(ReadUtil.readFloat());

        Disco disco = DiscoCatalogo.getInstance().getDiscoById();
        if(disco==null) { return false; }
        else { cancion.setDisco( disco ); }

        return true;

    }

    @Override
    public void processEditT(Cancion cancion) {
        System.out.println(" *** Id de la cancion " + cancion.getId());
        System.out.println(" *** Cancion a editar: " + cancion.getTitulo());
        System.out.print(" *** Teclee el nuevo nombre de la cancion");
        cancion.setTitulo(ReadUtil.read());
        System.out.print("> Ingrese la nueva duración de la canción en minutos: ");
        cancion.setDuracion( ReadUtil.readFloat() );

        Disco disco = DiscoCatalogo.getInstance().getDiscoById();
        if(disco==null)
        {
            System.out.println(" *** Disco no encontrado. No se pudo actualizar; compruébelo e inténtelo de nuevo.*** ");
        }
        else
        {
            cancion.setDisco( disco );
        }
    }

    @Override
    public File getFile() {
        return new File( "./src/main/fileStorage/Canciones.object");
    }

    @Override
    public void print() {
        GenericoJdbc<Cancion> cancionJdbc = new CancionJdbcImplementacion();
        cancionJdbc.findAll().stream().forEach(System.out::println);
    }
}
