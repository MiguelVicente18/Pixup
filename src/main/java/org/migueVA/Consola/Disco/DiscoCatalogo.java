package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.ArtistaJdbcImplementacion;
import org.migueVA.Jdbc.Implementacion.DiscoJdbcImplementacion;
import org.migueVA.Jdbc.Implementacion.DisqueraJdbcImplementacion;
import org.migueVA.Jdbc.Implementacion.GeneroMusicalJdbcImplementacion;
import org.migueVA.Model.Artista;
import org.migueVA.Model.Disco;
import org.migueVA.Model.Disquera;
import org.migueVA.Model.GeneroMusical;
import org.migueVA.Util.ReadUtil;
import org.migueVA.Ventana.Menu;

import java.io.File;

public class DiscoCatalogo extends GestorCatalogos<Disco> {
    private static DiscoCatalogo discoCatalogo;
    private static final GenericoJdbc<Disco> discoJdbc = DiscoJdbcImplementacion.getInstance();

    private DiscoCatalogo()
    {
        super(DiscoJdbcImplementacion.getInstance());
    }

    public static DiscoCatalogo getInstance()
    {
        if(discoCatalogo==null)
        {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT() {
        return new Disco();
    }

    @Override
    public boolean processNewT(Disco disco) {
        System.out.print(" ***  Ingrese el título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );
        System.out.print(" *** Ingrese el precio de venta: ");
        disco.setPrecio( ReadUtil.readFloat() );
        System.out.print(" *** Ingrese el número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el descuento actual (si tiene): ");
        disco.setDescuento( ReadUtil.readFloat() );
        System.out.print(" *** Ingrese la fecha de lanzamiento, en formato 'YYYY-MM-DD': ");
        disco.setFechaLanzamiento( ReadUtil.read() );
        System.out.print(" *** Ingrese la imagen: ");
        disco.setImagen( ReadUtil.read() );

        System.out.print("> Ingrese el ID de la disquera de su distribución: ");
        Disquera disquera = DisqueraJdbcImplementacion.getInstance().findById( ReadUtil.readInt() );
        if(disquera==null) { return false; }
        else { disco.setDisquera( disquera ); }

        System.out.print(" *** Ingrese el ID del artista al que pertenece: ");
        Artista artista = ArtistaJdbcImplementacion.getInstance().findById( ReadUtil.readInt() );
        if(artista==null) { return false; }
        else { disco.setArtista(artista); }

        System.out.print(" *** Ingrese el ID del género musical al que pertenece: ");
        GeneroMusical generoMusical = GeneroMusicalJdbcImplementacion.getInstance().findById( ReadUtil.readInt() );
        if(generoMusical==null) { return false; }
        else { disco.setGeneroMusical( generoMusical ); }

        discoJdbc.save(disco);
        return true;
    }

    @Override
    public void edit(Disco disco) {
        System.out.print(" *** Ingrese el ID del disco a editar: ");
        disco.setId( ReadUtil.readInt() );
        System.out.print(" ***Ingrese el nuevo título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );
        System.out.print(" *** Ingrese el nuevo precio de venta: ");
        disco.setPrecio( ReadUtil.readFloat() );
        System.out.print(" *** Ingrese el nuevo número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );
        System.out.print(" *** Ingrese el nuevo descuento actual (si tiene): ");
        disco.setDescuento( ReadUtil.readFloat() );

        discoJdbc.update(disco);
    }
}

