package org.migueVA.Consola.Disco;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.DisqueraJdbcImplementacion;
import org.migueVA.Model.Disquera;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class DisqueraCatalogo extends GestorCatalogos<Disquera>
{
    private static DisqueraCatalogo disqueraCatalogo;

    private DisqueraCatalogo()
    {
        super();
    }

    public static DisqueraCatalogo getInstance()
    {
        if(disqueraCatalogo==null)
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
        System.out.print("*** Ingrese el nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        return true;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println("\n*** ID de la disquera siendo editada: "+disquera.getId());
        System.out.println("*** Nombre de la disquera siendo editada: "+disquera.getDisquera());
        System.out.print("*** Ingrese el nuevo nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
    }

    @Override
    public File getFile() {
        return new File( "./src/main/fileStorage/Disqueras.list" );
    }

    @Override
    public void print()
    {
        GenericoJdbc<Disquera> disqueraJdbc = new DisqueraJdbcImplementacion();
        disqueraJdbc.findAll().stream().forEach(System.out::println);
    }

    public Disquera getDisqueraById() {
        if (isListEmpty()) {
            System.out.println(" *** No hay disqueras registradas.");
            return null;
        }
        while (true) {
            System.out.print(" *** Ingrese el ID de la disquera: ");
            int id = ReadUtil.readInt();
            Disquera disquera = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (disquera != null) {
                return disquera;
            }
            System.out.println(" ***  ID incorrecto, inténtelo nuevamente.");
        }
    }
}