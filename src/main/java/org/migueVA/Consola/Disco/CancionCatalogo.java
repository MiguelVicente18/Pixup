package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Cancion;
import org.migueVA.Model.Disco;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.CancionHiberImpl;
import org.migueVA.SqlHibernate.Impl.DiscoHiberImpl;
import org.migueVA.Util.ReadUtil;

import java.io.File;
import java.time.LocalTime;
import java.util.List;

public class CancionCatalogo extends GestorCatalogos<Cancion> {

    private static CancionCatalogo cancionCatalogo;
    private static final GenericoSQL<Cancion> cancionSql  = CancionHiberImpl.getInstance();

    private CancionCatalogo()
    {
        super(CancionHiberImpl.getInstance());
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
        cancion.setTituloCancion( ReadUtil.read() );
        System.out.print(" *** Ingrese la duración de la canción en minutos: ");
        String duracionStr = ReadUtil.read();

        try {
            String[] partes = duracionStr.split(":"); // Separa los minutos y los segundos
            int minutos = Integer.parseInt(partes[0]);
            int segundos = Integer.parseInt(partes[1]);

            LocalTime duracion = LocalTime.of(0, minutos, segundos); // HH:MM:SS
            cancion.setDuracion(duracion);
        }
        catch (Exception e)
        {
            System.out.println(" *** Duración inválida. *** ");
        }

        DiscoHiberImpl discoHiber = DiscoHiberImpl.getInstance();
        List<Disco> discoList = discoHiber.findAll();
        discoList.forEach(System.out::println);

        System.out.print(" ***  Ingrese el ID del disco al que pertenece: ");
        Disco disco = discoHiber.findById( ReadUtil.readInt() );
        if(disco==null)
        {
            System.out.println(" *** No encontrado. *** ");
            return false;
        }
        else
        {
            cancion.setDisco( disco );
        }

        cancionSql.save(cancion);
        return true;
    }

    @Override
    public boolean processEditT(Cancion cancion) {
        System.out.print(" *** Ingrese el nuevo título de la canción: ");
        cancion.setTituloCancion( ReadUtil.read() );

        System.out.print(" *** Ingrese la duración de la canción: ");
        cancion.setTituloCancion( ReadUtil.read() );

        cancionSql.update(cancion);
        return true;
    }
}
