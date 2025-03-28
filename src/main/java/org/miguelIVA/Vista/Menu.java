package org.miguelIVA.Vista;

public class Menu {
    public static void mostrarMenuPrincipal() {

        System.out.println(" *** DIRECCION *** ");
        System.out.println(" *** Bienvenido al sistema de gestión *** ");
        System.out.println(" 1.- Dar de alta un Estado" +
                "\n 2.- Dar de Baja un Estado" +
                "\n 3.- Gestionar Municipios y Colonias" +
                "\n 4.- Ver todos los Registros Guardados" +
                "\n 5.- Actualizar un Registro" +
                "\n 6.- Salir");
    }

    public static void mostrarMenuGestion() {
        System.out.println(" *** Gestion Municipios y Colonias *** ");
        System.out.println(" 1.- Dar de alta un Municipio" +
                "\n 2.- Dar de Alta una Colonia" +
                "\n 3.- Dar de Baja un Municipio" +
                "\n 4.- Dar de Baja una Colonia" +
                "\n 5.- Volver al Menu Principal");
    }

    public static void opcionInvalida() {
        System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
    }

    public static void errorDato( )
    {
        System.out.println("No es un dato valido");
    }

    public static void seleccionDato(){
        System.out.print( " Ingrese Su Preferencia: ");
    }
}
