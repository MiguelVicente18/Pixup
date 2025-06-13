package org.migueVA.Consola.enums;

import org.migueVA.Util.Ejecutable;
import org.migueVA.Util.SeccionCatalogos;

public enum MenuConsolaEnum{
    CATALOGO(1, SeccionCatalogos.getInstance()),
    PENDIENTE(2, null),
    SALIR(3, null),
    OPCION_ERRONEA(4, null);

    private Integer id;
    private Ejecutable ejecutable;

    MenuConsolaEnum(Integer id, Ejecutable ejecutable) {
        this.id = id;
        this.ejecutable = ejecutable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ejecutable getEjecutable() {
        return ejecutable;
    }

    public void setEjecutable(Ejecutable ejecutable) {
        this.ejecutable = ejecutable;
    }

    public static MenuConsolaEnum getTipoEjecutableById(Integer opcion) {
        return switch (opcion) {
            case 1 -> CATALOGO;
            case 2 -> PENDIENTE;
            case 3 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }
}
