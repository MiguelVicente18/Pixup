package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.MunicipioJdbcImplementacion;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends GestorCatalogos<Municipio> {

    private static MunicipioCatalogo municipioCatalogo;
    private final EstadoCatalogo estadoCatalogo;

    private MunicipioCatalogo() {
        super();
        estadoCatalogo = EstadoCatalogo.getInstance();
    }

    public static MunicipioCatalogo getInstance() {
        if (municipioCatalogo == null) {
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
        Estado estado = estadoCatalogo.getEstadoById();

        if(estado==null)
        {
            return false;
        }
        municipio.setEstado(estado);
        return true;
    }

    @Override
    public void processEditT(Municipio municipio) {
        System.out.print(" *** ID del Municipio : " + municipio.getId());
        System.out.print(" *** Municipio en Edición : " + municipio.getNombre());
        System.out.print(" *** Teclee el nuevo nombre del estado : ");
        municipio.setNombre(ReadUtil.read());

        System.out.println(" *** Ingrese el ID del nuevo Estado para este Municipio: ");
        Estado estado = estadoCatalogo.getEstadoById();

        if(estado==null)
        {
            System.out.println("*** Estado no encontrado. No se pudo actualizar el estado del municipio, compruébelo e inténtelo de nuevo. *** ");
        }
        municipio.setEstado(estado);
    }

    @Override
    public File getFile() {
        return new File("./src/main/fileStorage/Municipios.list");
    }

    @Override
    public void print() {
        GenericoJdbc<Municipio> municipioJdbc = new MunicipioJdbcImplementacion();
        municipioJdbc.findAll().stream().forEach(System.out::println);
    }

    public Municipio getMunicipioById() {
        if (isListEmpty()) {
            System.out.println("*** No hay municipios registrados.");
            return null;
        }
        while (true) {
            System.out.print("*** Ingrese el ID del municipio: ");
            int id = ReadUtil.readInt();
            Municipio municipio = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (municipio != null) {
                return municipio;
            }
            System.out.println("*** ID incorrecto, inténtelo nuevamente.");
        }
    }
}