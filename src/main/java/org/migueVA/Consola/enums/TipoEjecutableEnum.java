package org.migueVA.Consola.enums;

import org.migueVA.Consola.Consola;
import org.migueVA.Util.Ejecutable;
import org.migueVA.Ventana.Ventana;

public enum TipoEjecutableEnum {
    CONSOLA( 1, Consola.getInstance() ),
    VENTANA( 2, Ventana.getInstance() ),
    SALIR( 3, null ),
    OPCION_ERRONEA( 4, null );

    private Integer id;
    private Ejecutable ejecutable;

    TipoEjecutableEnum(Integer id, Ejecutable ejecutable) {
        this.id = id;
        this.ejecutable = ejecutable;
    }

    public Integer getId() {
        return id;
    }

    public Ejecutable getEjecutable() {
        return ejecutable;
    }

    public static TipoEjecutableEnum getTipoEjecutableById (Integer opcion ) {
        switch (opcion) {
            case 1:
                return CONSOLA;
            case 2:
                return VENTANA;
            case 3:
                return SALIR;
            default:
                return OPCION_ERRONEA;

        }
    }
}
