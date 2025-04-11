package org.migueVA.Consola.Catalogos;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Catalogo;
import org.migueVA.Util.ReadUtil;
import org.migueVA.Ventana.LecturaAccion;
import org.migueVA.Ventana.Menu;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class GestorCatalogos<T extends Catalogo>  extends LecturaAccion {

    protected List<T> list;
    protected T t;
    protected boolean flag2;
    protected GenericoJdbc<T> genericoJdbc;

    public GestorCatalogos(GenericoJdbc<T> genericoJdbc) {
        this.genericoJdbc = genericoJdbc;
        Conexion conexion = new Conexion() {
        };
        Connection connection = conexion.getConnection();
        //list = new ArrayList<>();
    }

    /*
    public boolean isListEmpty() {
        return list.isEmpty();
    }*/


    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract void edit(T t);

    public void print(){
        List<T> list  = genericoJdbc.findAll();
        if (list.isEmpty()){
            System.out.println(" *** No hay Elementos Registrados *** ");
        }
        list.stream().forEach(System.out::println);
    }


    public void add() {
        t = newT();
        if (processNewT(t)) {
            System.out.println(" *** Elemento Añadido *** ");
        }
    }

    public void remove() {
        List<T> list = genericoJdbc.findAll();
        if (list.isEmpty()) {
            System.out.println("*** No hay elementos para Eliminar ***");
            return;
        }
        flag2 = true;
        while (flag2) {
            System.out.println("***  Ingrese el ID del elemento a Eliminar: ");
            t = list.stream().filter(e -> e.getId().equals(ReadUtil.readInt())).findFirst().orElse(null);
            if (t == null) {
                System.out.println("*** ID incorrecto, intentelo nuevamente ***");
            } else {
                if (genericoJdbc.delete(t)){
                    System.out.println(" *** Elemento Eliminado *** ");
                }
                flag2 = false;
            }
        }
    }

    public void findById() {
        System.out.print(" ***  Ingresa un ID para buscar: ");
        t = genericoJdbc.findById( ReadUtil.readInt() );

        if(t!=null)
        {
            System.out.println(t);
        }
        else
        {
            System.out.println(" *** No existe un elemento con dicho ID.");
        }
    }

    public void despliegaMenu() {
        System.out.println("\n\t:: Gestión de catálogos ::");
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Agregar");
        System.out.println("2.- Eliminar");
        System.out.println("3.- Editar");
        System.out.println("4.- Imprimir elementos en lista");
        System.out.println("5.- Obtener por su Id");
        System.out.println("6.- Salir");
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
        return 6;
    }

    @Override
    public void procesaOpcion() {
        switch (opcion) {
            case 1:
                add();
                break;
            case 2:
                remove();
                break;
            case 3:
                edit(t);
                break;
            case 4:
                print();
                break;
            case 5:
                findById();
            default:
                Menu.opcionInvalida();
        }
    }
}