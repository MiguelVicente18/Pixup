package org.migueVA.Consola;

import org.migueVA.Model.Disco;
import org.migueVA.Util.ReadUtil;

import java.io.File;
import java.util.Optional;

public class DiscoCatalogo extends Catalogos <Disco>{

    private static DiscoCatalogo discoCatalogo;

    private DiscoCatalogo() {
    }

    public static DiscoCatalogo getInstance()
    {
        if(discoCatalogo == null)
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
        System.out.print(" *** Escriba el Titulo del Disco : ");
        disco.setTitulo(ReadUtil.read());

        System.out.print(" *** Ingrese el Precio del Disco : ");
        disco.setPrecio(ReadUtil.readFloat());

        System.out.print(" *** Ingrese la existencia del Disco : ");
        disco.setExistencia(ReadUtil.readInt());

        System.out.print(" *** Ingrese el descuento aplicado al disco: ");
        disco.setDescuento(ReadUtil.readFloat());

        System.out.print(" *** Ingrese la fecha de lanzamiento del disco: ");
        disco.setFechaLanzamiento(ReadUtil.read());

        System.out.print(" *** Ingrese la URL de la imagen del disco: ");
        disco.setImagen(ReadUtil.read());

        System.out.print(" *** Seleccione el ID de la disquera: ");
        DisqueraCatalogo.getInstance().print();
        int idDisquera = ReadUtil.readInt();
        disco.setIdDisquera(idDisquera);

        System.out.print(" *** Seleccione el ID del artista: ");
        ArtistaCatalogo.getInstance().print();
        int idArtista = ReadUtil.readInt();
        disco.setIdArtista(idArtista);

        System.out.println(" *** Seleccione el ID del género musical: ");
        GeneroMusicalCatalogo.getInstance().print();
        int idGeneroMusical = ReadUtil.readInt();
        disco.setIdGeneroMusical(idGeneroMusical);
        return true;
    }

    @Override
    public void processEditT(Disco disco) {
        System.out.println(" *** ID del disco: " + disco.getId());
        System.out.println(" *** Título actual: " + disco.getTitulo());
        System.out.print(" *** Ingrese el nuevo título del disco: ");
        disco.setTitulo(ReadUtil.read());

        System.out.println(" ***Precio actual: " + disco.getPrecio());
        System.out.print(" *** Ingrese el nuevo precio del disco: ");
        disco.setPrecio(ReadUtil.readFloat());

        System.out.println(" *** Existencia actual: " + disco.getExistencia());
        System.out.print(" *** Ingrese la nueva existencia del disco: ");
        disco.setExistencia(ReadUtil.readInt());

        System.out.println(" *** Descuento actual: "+disco.getDescuento());
        System.out.print(" *** Ingrese el nuevo descuento para el disco: ");
        disco.setDescuento(ReadUtil.readFloat());

        System.out.println(" *** Fecha de lanzamiento actual: "+ disco.getFechaLanzamiento());
        System.out.print(" *** Ingrese la nueva fecha de lanzamiento: ");
        disco.setFechaLanzamiento(ReadUtil.read());

        System.out.println(" *** URl actual de la imagen del disco: "+disco.getImagen());
        System.out.print (" *** Ingrese la nueva URL de la imagen del disco: ");
        disco.setImagen(ReadUtil.read());

        System.out.println(" *** ID actual de la disquera: "+disco.getIdDisquera());
        System.out.print(" *** Seleccione el nuevo ID de la disquera: ");
        DisqueraCatalogo.getInstance().print();
        int idDisquera = ReadUtil.readInt();
        disco.setIdDisquera(idDisquera);

        System.out.println(" *** ID actual del artista: "+disco.getIdArtista());
        System.out.print(" *** Seleccione el nuevo ID del artista: ");
        ArtistaCatalogo.getInstance().print();
        int idArtista = ReadUtil.readInt();
        disco.setIdArtista(idArtista);

        System.out.println(" *** ID actual del género musical: "+disco.getIdGeneroMusical());
        System.out.print(" *** Seleccione el nuevo ID del género musical: ");
        GeneroMusicalCatalogo.getInstance().print();
        int idGeneroMusical = ReadUtil.readInt();
        disco.setIdGeneroMusical(idGeneroMusical);
    }

    @Override
    public File getFile() {
        return new File("Disco.list");
    }

    public Disco getDiscoById(int idDisco) {
        Optional<Disco> disco = list.stream()
                .filter(d -> d.getId().equals(idDisco))
                .findFirst();
        return disco.orElse(null);
    }
}

