package org.migueVA.Consola;

import org.migueVA.Model.GeneroMusical;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical> {

    private static GeneroMusicalCatalogo generoMusicalCatalogo;

    private GeneroMusicalCatalogo() {
    }

    public static GeneroMusicalCatalogo getInstance()
    {   if(generoMusicalCatalogo == null)
        {
            generoMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return generoMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical generoMusical) {
        System.out.print(" *** Escriba el Genero Musical : ");
        generoMusical.setGeneroMusical(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(GeneroMusical generoMusical) {
        System.out.println(" *** Genero Musical a Editar : " + generoMusical.getGeneroMusical());
        System.out.println(" *** Id del Genero Musical a Editar : " + generoMusical.getId());
        System.out.print(" *** Ingrese el Nuevo Genero Musical : " );
        generoMusical.setGeneroMusical(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File ("/Disquera.list");
    }
}
