package org.migueVA.Inicio;

import org.migueVA.Consola.enums.TipoEjecutableEnum;
import org.migueVA.Util.Ejecutable;
import org.migueVA.Util.Funciones;
import org.migueVA.Util.ReadUtil;

public class Inicio {
    public static void main(String args[]) {
        int opcion;
        Ejecutable ejecutable;
        TipoEjecutableEnum tipoEjecutableEnum;
        while ( true ) {
            System.out.println(Funciones.menuBienvenida);
            opcion = ReadUtil.readInt(Funciones.seleccionarOpcion, 1, 3);

            tipoEjecutableEnum = TipoEjecutableEnum.getTipoEjecutableById( opcion );

            if ( TipoEjecutableEnum.SALIR.equals(tipoEjecutableEnum) ){
                return;
            } else if ( TipoEjecutableEnum.OPCION_ERRONEA.equals(tipoEjecutableEnum) ){
                Funciones.opcionInvalido();
            } else if ( tipoEjecutableEnum.getEjecutable() != null ){
                ejecutable = tipoEjecutableEnum.getEjecutable();
                ejecutable.run();
            }
        }
    }
}
