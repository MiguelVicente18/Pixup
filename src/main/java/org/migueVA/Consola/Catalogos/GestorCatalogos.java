package org.migueVA.Consola.Catalogos;

import org.migueVA.Jdbc.Conexiones.Conexion;
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
    protected File file;
    private Connection connection;

    public GestorCatalogos() {
        Conexion conexion = new Conexion() {
        };
        this.connection = conexion.getConnection();
        list = new ArrayList<>();
    }

    public boolean isListEmpty() {
        return list.isEmpty();
    }


    public abstract T newT();

    public abstract boolean processNewT(T t);

    public abstract void processEditT(T t);

    public abstract File getFile();

    public abstract void print();


    public void add() {
        t = newT();
        if (processNewT(t)) {
            t.setId(list.size() + 1);
            list.add(t);
            System.out.println(" *** Elemento Añadido *** ");
        }
    }

    public void edit() {
        if (isListEmpty()) {
            System.out.println("*** No hay elementos. ***");
            return;
        }
        flag2 = true;
        while (flag2) {
            System.out.println("***  Ingrese el ID del elemento a editar: ");
            print();
            t = list.stream().filter(e -> e.getId().equals(ReadUtil.readInt())).findFirst().orElse(null);
            if (t == null) {
                System.out.println("*** ID incorrecto, intentelo nuevamente ***");
            } else {
                processEditT(t);
                flag2 = false;
                System.out.println(" *** Elemento modificado ***");
            }
        }
    }

    public void remove() {
        if (isListEmpty()) {
            System.out.println("***No hay elementos***");
            return;
        }
        flag2 = true;
        while (flag2) {
            System.out.print("Ingrese el ID del elemento a borrar: ");
            print();
            t = list.stream().filter(e -> e.getId().equals(ReadUtil.readInt())).findFirst().orElse(null);
            if (t == null) {
                System.out.println("***ID incorrecto, inténtelo nuevamente.***");
            } else {
                list.remove(t);
                flag2 = false;
                System.out.println("***Elemento eliminado***");
            }
        }
    }

    private void guardarArchivo() {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try {
            if (isListEmpty()) {
                System.out.println("> No hay elementos para guardar.");
                return;
            }
            file = getFile();
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

            oos.close();
            fos.close();

            System.out.println("> Datos guardados con éxito.");
        } catch (IOException e) {
            System.err.println("> Error al guardar: " + e.getMessage());
        }
    }

    private void leerArchivo() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            file = getFile();

            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            list = (List<T>) ois.readObject();

            ois.close();
            fis.close();

            System.out.println("> Datos cargados con éxito.");
        } catch (IOException e) {
            System.err.println("> Error al cargar: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void despliegaMenu() {
        System.out.println("\n\t:: Gestión de catálogos ::");
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Agregar");
        System.out.println("2.- Eliminar");
        System.out.println("3.- Editar");
        System.out.println("4.- Imprimir elementos en lista");
        System.out.println("5.- Guardar en archivo");
        System.out.println("6.- Leer en archivo");
        System.out.println("7.- Salir");
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
        return 7;
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
                edit();
                break;
            case 4:
                print();
                break;
            case 5:
                guardarArchivo();
                break;
            case 6:
                leerArchivo();
                break;
            default:
                Menu.opcionInvalida();
        }
    }
}