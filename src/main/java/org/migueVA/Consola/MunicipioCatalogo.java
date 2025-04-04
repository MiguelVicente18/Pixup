package org.migueVA.Consola;

import org.migueVA.Model.Municipio;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends Catalogos<Municipio> {

    private static MunicipioCatalogo municipioCatalogo;

    private MunicipioCatalogo(){
        super();
    }

    public static MunicipioCatalogo getInstance(){
        if(municipioCatalogo == null){
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    @Override
    public Municipio newT() {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio municipio) {
        System.out.print(" *** Teclee el Nombre del Estado: ");
        municipio.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Municipio municipio) {
        System.out.print(" *** ID del Municipio : " + municipio.getId());
        System.out.print(" *** Municipio en Edición : " + municipio.getNombre());
        System.out.print(" *** Teclee el nuevo nombre del estado : ");
        municipio.setNombre(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File("./Municipio.object");
    }
}
