package org.miguelIVA.Inicio;

import org.miguelIVA.Util.ReadUtil;
import org.miguelIVA.Vista.Menu;
import org.miguelIVA.model.GestorEntidades;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        int opcion;

        GestorEntidades gestor = new GestorEntidades();

        while(flag) {
            Menu.mostrarMenuPrincipal();
            Menu.seleccionDato();
            opcion = ReadUtil.readInt();

            switch (opcion) {
                case 1:
                    gestor.darDeAltaEstado();
                    break;
                case 2:
                    gestor.darDeBajaEstado();
                    break;
                case 3:
                    gestor.gestionarMYC();
                    break;
                case 4:
                    gestor.VerRegistros();
                    break;
                case 5:
                    gestor.ActualizarRegistro();
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    Menu.opcionInvalida();
                    break;
            }
        }
    }
}
