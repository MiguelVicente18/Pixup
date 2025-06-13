package org.migueVA.Consola.enums;

public enum AbcEnum {
    ALTA(1),
    BAJA(2),
    CONSULTAS(3),
    CAMBIOS(4),
    SALIR(5),
    OPCION_ERRONEA(6);

    private Integer tipo;

    AbcEnum(Integer tipo){
        this.tipo = tipo;
    }

    public Integer getTipo(){
        return tipo;
    }

    public void setTipo(Integer tipo){
        this.tipo = tipo;
    }

    public static AbcEnum getAbmByTipo ( Integer tipo ) {
        return switch (tipo) {
            case 1 -> ALTA;
            case 2 -> BAJA;
            case 3 -> CONSULTAS;
            case 4 -> CAMBIOS;
            case 5 -> SALIR;
            default -> OPCION_ERRONEA;
        };
    }
}
