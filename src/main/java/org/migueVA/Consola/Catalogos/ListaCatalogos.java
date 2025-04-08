package org.migueVA.Consola.Catalogos;

import org.migueVA.Consola.Disco.*;
import org.migueVA.Consola.Usuario.ColoniaCatalogo;
import org.migueVA.Consola.Usuario.EstadoCatalogo;
import org.migueVA.Consola.Usuario.MunicipioCatalogo;
import org.migueVA.Util.Ejecutable;
import org.migueVA.Ventana.LecturaAccion;
import org.migueVA.Ventana.Menu;

public class ListaCatalogos extends LecturaAccion {

    private static ListaCatalogos listaCatalogos;

    private  ListaCatalogos() {
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
        System.out.println("\n\t::: Lista de Catálogos Disponibles :::");
        System.out.println( "1.- Usuarios");
        System.out.println( "2.- Discos");
        System.out.println( "3.- Salir");
        Menu.seleccionaOpcion();
    }
    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 3;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch(opcion)
        {
            case 1:
                ejecutable = ListaUsuario.getInstance();
                break;
            case 2:
                ejecutable = ListaDisco.getInstance();
                break;
            case 3:
                flag = false;
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
        if(ejecutable!=null)
        {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
