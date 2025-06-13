package org.migueVA.Consola;

import org.migueVA.Consola.enums.MenuConsolaEnum;
import org.migueVA.Util.Ejecutable;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;

public class Consola implements Ejecutable {
    private static Consola consola;
    private Consola() {
    }
    public static Consola getInstance( ) {
        if(consola == null) {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void run() {
        int opcion;
        Ejecutable ejecutable;
        MenuConsolaEnum tipoEjecutable;
        while (true) {
            System.out.println(Funciones.menuConsola);
            opcion = ReadUtil.readInt(Funciones.seleccionarOpcion, 1, 3);

            tipoEjecutable = MenuConsolaEnum.getTipoEjecutableById( opcion );

            if ( MenuConsolaEnum.SALIR.equals(tipoEjecutable) ){
                return;
            } else if ( MenuConsolaEnum.OPCION_ERRONEA.equals(tipoEjecutable) ){
                Funciones.opcionInvalido();
            } else if ( tipoEjecutable.getEjecutable() != null ){
                ejecutable = tipoEjecutable.getEjecutable();
                ejecutable.run();
            }

        }
    }

}
