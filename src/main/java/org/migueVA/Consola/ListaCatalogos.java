package org.migueVA.Consola;

import org.migueVA.Model.Cancion;
import org.migueVA.Util.Ejecutable;
import org.migueVA.Ventana.LecturaAccion;

public class ListaCatalogos extends LecturaAccion {

    private static ListaCatalogos listaCatalogos;

    private  ListaCatalogos()
    {
    }

    public static ListaCatalogos getInstance( )
    {
        if(listaCatalogos==null)
        {
            listaCatalogos = new ListaCatalogos();
        }
        return listaCatalogos;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println( ":: Seleccione una opcion ::" );
        System.out.println( "1.- Estado");
        System.out.println( "2.- Municipio");
        System.out.println( "3.- Colonia");
        System.out.println( "4.- Artista");
        System.out.println( "5.- Cancion");
        System.out.println( "6.- Disco");
        System.out.println( "7.- Disquera");
        System.out.println( "8.- Genero Musicañ");
        System.out.println( "9.- Salir");
        System.out.print( " *** La Eleccion que desea es :  " );
    }
    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 9;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                ejecutable = EstadoCatalogo.getInstance( );
                break;
            case 2:
                ejecutable = MunicipioCatalogo.getInstance( );
                break;
            case 3:
                ejecutable = ColoniaCatalogo.getInstance( );
                break;
            case 4:
                ejecutable = ArtistaCatalogo.getInstance();
                break;
            case 5:
                ejecutable = CancionCatalogo.getInstance();
                break;
            case 6:
                ejecutable = DiscoCatalogo.getInstance();
                break;
            case 7:
                ejecutable = DisqueraCatalogo.getInstance();
                break;
            case 8:
                ejecutable = GeneroMusicalCatalogo.getInstance();
                break;
        }
        ejecutable.setFlag( true );
        ejecutable.run( );

    }
}
