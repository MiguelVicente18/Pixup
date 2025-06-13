package org.migueVA.Util;

public class Funciones {

    public static String menuBienvenida = (
            " * * * ¡Bienvenido a Pixup!  * * * "+
            "!\n* *  Selecciona una Opcion * *"+
            "\n --> 1.- CONSOLA " +
            "\n --> 2.- VENTANA " +
            "\n --> 3.- SALIR"
            );

    public static String menuConsola = (
            " * - * Menú Principal * - * " +
            "\n << Eliga la Opcion que Desea Realizar >> " +
            "\n --> 1.- CATALOGO " +
            "\n --> 2.- PENDIENTE " +
            "\n --> 3.- SALIR"
            );

    public static String menuPrincipal = (
            " - * - Catalogos - * - " +
            "\n --> 1.- ESTADO " +
            "\n --> 2.- MUNICIPIO " +
            "\n --> 3.- COLONIA " +
            "\n --> 4.- ARTISTA " +
            "\n --> 5.- DISQUERA " +
            "\n --> 6.- GENERO MUSICAL " +
            "\n --> 7.- CANCION " +
            "\n --> 8.- DISCO " +
            "\n --> 9.- SALIR "
            );

    public static String menuABC = (
            " ** Que desea Realizar : **" +
            "\n --> 1.- ALTA" +
            "\n --> 2.- BAJA" +
            "\n --> 3.- CONSULTAS" +
            "\n --> 4.- CAMBIOS" +
            "\n --> 5.- SALIR"
            );

    public static String leerNombre = " --> ¿Cúal es el nombre?  ";
    public static String nuevoNombre = " --> ¿Cúal es el Nuevo Nombre?  ";
    public static String seleccionarOpcion = " --> Selecciona una Opcion :  ";

    public static void opcionInvalido(){
        System.out.println( " x x x La Opción no es Correcta x x x ");
    }
    public static void errorDato(){
        System.out.println( " x x x No es un Dato Valido x x x ");
    }







}
