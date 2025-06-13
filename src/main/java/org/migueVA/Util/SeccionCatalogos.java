package org.migueVA.Util;

import org.migueVA.Consola.enums.AbcEnum;
import org.migueVA.Consola.enums.TipoCatEnum;
import org.migueVA.Model.ClaseCatalogo;

public class SeccionCatalogos implements Ejecutable{

    private static SeccionCatalogos catalogos;

    private SeccionCatalogos(){
    }

    public static SeccionCatalogos getInstance() {
        if (catalogos == null) {
            catalogos = new SeccionCatalogos();
        }
        return catalogos;
    }


    @Override
    public void run() {
        int opcion;
        TipoCatEnum tipoCatEnum;
        ClaseCatalogo claseCatalogo;

        while(true){
            System.out.println(Funciones.menuPrincipal);
            opcion = ReadUtil.readInt(Funciones.seleccionarOpcion, 1, 9);

            tipoCatEnum = TipoCatEnum.getCatalogoByTipo(opcion );

            if(TipoCatEnum.SALIR.equals(tipoCatEnum)){
                return;
            }else if(TipoCatEnum.OPCION_ERRONEA.equals(tipoCatEnum)){
                Funciones.opcionInvalido();
                continue;
            }

            claseCatalogo = tipoCatEnum.getCatalogo();

            if (claseCatalogo != null) {
                boolean continuarAbc = true;
                while (continuarAbc) {
                    System.out.println(Funciones.menuABC);
                    opcion = ReadUtil.readInt(Funciones.seleccionarOpcion, 1, 5);
                    AbcEnum abcEnum = AbcEnum.getAbmByTipo(opcion);

                    if (AbcEnum.SALIR.equals(abcEnum)) {
                        continuarAbc = false;
                    } else if (AbcEnum.OPCION_ERRONEA.equals(abcEnum)) {
                        Funciones.opcionInvalido();
                    } else {
                        switch (abcEnum) {
                            case ALTA -> {
                                System.out.println("- - - - ALTA - - - -");
                                claseCatalogo.alta();
                                System.out.println(" - - - - - - - - - - - - -  - ");
                            }
                            case BAJA -> {
                                System.out.println("- - - - BAJA - - - - ");
                                claseCatalogo.baja();
                                System.out.println(" - - - - - - - - - - - - -");
                            }
                            case CONSULTAS-> {
                                System.out.println("- - - - CONSULTAS - - - - ");
                                claseCatalogo.consulta();
                                System.out.println(" - - - - - - - - - - - - -");
                            }
                            case CAMBIOS -> {
                                System.out.println("- - - - CAMBIOS - - - - ");
                                claseCatalogo.cambio();
                                System.out.println(" - - - - - - - - - - - - -");
                            }
                        }
                    }
                }
            }
            }
        }

    }
