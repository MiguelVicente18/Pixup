package org.migueVA.Consola.Catalogos;

import org.migueVA.Consola.Usuario.ColoniaCatalogo;
import org.migueVA.Consola.Usuario.EstadoCatalogo;
import org.migueVA.Consola.Usuario.MunicipioCatalogo;
import org.migueVA.Util.Ejecutable;
import org.migueVA.Ventana.LecturaAccion;
import org.migueVA.Ventana.Menu;

public class ListaUsuario extends LecturaAccion {

    private static ListaUsuario listaUsuario;

    private ListaUsuario() {
    }

    public static ListaUsuario getInstance(){
        if(listaUsuario==null){
            listaUsuario = new ListaUsuario();
        }
        return listaUsuario;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("\n *** : Catálogo de Usuarios : ***");
        System.out.println( " *** : 1.- Estado");
        System.out.println( " *** : 2.- Municipio");
        System.out.println( " *** : 3.- Colonia");
        System.out.println( " *** : 4.- Salir");
        Menu.seleccionaOpcion();
    }

    @Override
    public int valorMinMenu() {
        return 1;
    }

    @Override
    public int valorMaxMenu() {
        return 4;
    }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        switch (opcion){
            case 1:
                ejecutable = EstadoCatalogo.getInstance();
                break;
            case 2:
                ejecutable = MunicipioCatalogo.getInstance();
                break;
            case 3:
                ejecutable = ColoniaCatalogo.getInstance();
                break;
            case 4:
                flag=false;
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
        if(ejecutable!=null)
        {
            ejecutable.setFlag( true );
            ejecutable.run( );
        }
    }
}
