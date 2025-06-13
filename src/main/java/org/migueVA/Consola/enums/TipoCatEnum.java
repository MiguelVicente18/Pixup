package org.migueVA.Consola.enums;

import lombok.Getter;
import org.migueVA.Model.ClaseCatalogo;
import org.migueVA.Model.disco.*;
import org.migueVA.Model.domicilio.Colonia;
import org.migueVA.Model.domicilio.Estado;
import org.migueVA.Model.domicilio.Municipio;

public enum TipoCatEnum {
    ESTADO(1, Estado.getManage()),
    MUNICIPIO(2, Municipio.getManage()),
    COLONIA(3, Colonia.getManage()),
    ARTISTA(4, Artista.getManage()),
    DISQUERA(5, Disquera.getManage()),
    GENERO_MUSICAL(6, GeneroMusical.getManage()),
    CANCION(7, Cancion.getManage()),
    DISCO(8, Disco.getManage()),
    SALIR(9, null),
    OPCION_ERRONEA(-1, null);

    @Getter
    private final int tipo;
    private final ClaseCatalogo claseCatalogo;

    TipoCatEnum(int tipo, ClaseCatalogo claseCatalogo){
        this.tipo = tipo;
        this.claseCatalogo = claseCatalogo;
    }

    public ClaseCatalogo getCatalogo() {
        return claseCatalogo;
    }
    public static TipoCatEnum getCatalogoByTipo(int tipo) {
        for (TipoCatEnum tipoCatalogoEnum : values()) {
            if (tipoCatalogoEnum.tipo == tipo) {
                return tipoCatalogoEnum;
            }
        }
        return OPCION_ERRONEA;
    }
}
