package org.migueVA.Consola;

import org.migueVA.Model.Disquera;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class DisqueraCatalogo extends Catalogos<Disquera> {

    private static DisqueraCatalogo disqueraCatalogo;

    private DisqueraCatalogo() {
    }

    public static DisqueraCatalogo getInstance()
    {
        if(disqueraCatalogo== null)
        {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera) {
        System.out.print(" *** Ingrese el Nombre de la Disquera : ");
        disquera.setDisquera(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println(" +++ El Nombre de la Disquera es : " + disquera.getDisquera());
        System.out.println(" *** El nombre del Artista es : " + disquera.getDisquera());
        System.out.print(" *** Escribe el Nombre de la Disquera : ");
        disquera.setDisquera(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File("./Disquera.object");
    }
}
