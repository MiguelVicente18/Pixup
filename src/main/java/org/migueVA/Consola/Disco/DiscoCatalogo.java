package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Model.Artista;
import org.migueVA.Model.Disco;
import org.migueVA.Model.Disquera;
import org.migueVA.Model.GeneroMusical;
import org.migueVA.SqlHibernate.GenericoSQL;
import org.migueVA.SqlHibernate.Impl.ArtistaHiberImpl;
import org.migueVA.SqlHibernate.Impl.DiscoHiberImpl;
import org.migueVA.SqlHibernate.Impl.DisqueraHiberImpl;
import org.migueVA.SqlHibernate.Impl.GeneroMusicalHiberImpl;
import org.migueVA.Util.ReadUtil;
import org.migueVA.Ventana.Menu;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DiscoCatalogo extends GestorCatalogos<Disco> {
    private static DiscoCatalogo discoCatalogo;
    private static final GenericoSQL<Disco> discoSql = DiscoHiberImpl.getInstance();

    private DiscoCatalogo()
    {
        super(DiscoHiberImpl.getInstance());
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
        System.out.print(" *** : Ingrese el título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );

        System.out.print(" *** : Ingrese el precio de venta: ");
        disco.setPrecio( ReadUtil.readFloat() );

        System.out.print(" *** : Ingrese el número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );

        System.out.print("*** : Ingrese el descuento actual : ");
        disco.setDescuento( ReadUtil.readFloat() );

        System.out.print("*** : Ingrese la fecha de lanzamiento, en formato: ");
        String fechaStr = ReadUtil.read();
        LocalDate fecha = LocalDate.parse(fechaStr);
        disco.setFechaLanzamiento( fecha );

        System.out.print("*** : Ingrese la imagen: ");
        disco.setImagen( ReadUtil.read() );

        DisqueraHiberImpl disqueraSql = DisqueraHiberImpl.getInstance();
        List<Disquera> disqueraList = disqueraSql.findAll();
        disqueraList.forEach(System.out::println);

        System.out.print(" *** : Ingrese el ID de la disquera de su distribución: ");
        Disquera disquera = disqueraSql.findById( ReadUtil.readInt() );
        if(disquera==null)
        {
            System.out.println(" ***  No encontrado. ***");
            return false;
        }
        else
        {
            disco.setDisquera( disquera );
        }

        ArtistaHiberImpl artistaSql = ArtistaHiberImpl.getInstance();
        List<Artista> artistaList = artistaSql.findAll();
        artistaList.forEach(System.out::println);

        System.out.print(" *** : Ingrese el ID del artista al que pertenece: ");
        Artista artista = artistaSql.findById( ReadUtil.readInt() );
        if(artista==null)
        {
            System.out.println(" *** No encontrado. *** ");
            return false;
        }
        else
        {
            disco.setArtista(artista);
        }

        GeneroMusicalHiberImpl generoMusicalSql = GeneroMusicalHiberImpl.getInstance();
        List<GeneroMusical> generoMusicalList = generoMusicalSql.findAll();
        generoMusicalList.forEach(System.out::println);

        System.out.print(" *** : Ingrese el ID del género musical al que pertenece: ");
        GeneroMusical generoMusical = generoMusicalSql.findById( ReadUtil.readInt() );
        if(generoMusical==null)
        {
            System.out.println(" *** No encontrado. *** ");
            return false;
        }
        else
        {
            disco.setGeneroMusical( generoMusical );
        }

        discoSql.save(disco);
        return true;
    }

    @Override
    public boolean   processEditT(Disco disco) {
        System.out.print(" *** : Ingrese el nuevo título del disco: ");
        disco.setTituloDisco( ReadUtil.read() );
        System.out.print(" *** : Ingrese el nuevo precio de venta: ");
        disco.setPrecio( ReadUtil.readFloat() );
        System.out.print(" *** :Ingrese el nuevo número de copias en inventario: ");
        disco.setExistencias( ReadUtil.readInt() );
        System.out.print(" *** : Ingrese el nuevo descuento actual : ");
        disco.setDescuento( ReadUtil.readFloat() );

        discoSql.update(disco);
        return true;
    }
}

