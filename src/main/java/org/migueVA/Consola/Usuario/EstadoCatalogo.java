package org.migueVA.Consola.Usuario;

import org.migueVA.Consola.Catalogos.GestorCatalogos;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Jdbc.Implementacion.EstadoJdbcImplementacion;
import org.migueVA.Model.Estado;
import org.migueVA.Util.ReadUtil;

import java.io.File;

public class EstadoCatalogo extends GestorCatalogos<Estado> {

    private static EstadoCatalogo estadoCatalogo;
    private EstadoCatalogo(){
        super();
    }

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {estadoCatalogo = new EstadoCatalogo();}
        return estadoCatalogo;
    }

    @Override
    public Estado newT() {
        return new Estado();
    }

    @Override
    public boolean processNewT(Estado estado) {
        System.out.print(" *** Teclee el Nombre del Estado : ");
        estado.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Estado estado) {
        System.out.print(" *** Id del Estado : " + estado.getId());
        System.out.print(" *** Estado en Edición : " + estado.getNombre());
        System.out.print(" *** Teclee el Nuevo Nombre del Estado : ");
        estado.setNombre(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File( "./src/main/fileStorage/Estados.object"  );
    }

    @Override
    public void print() {
        GenericoJdbc<Estado> estadoJdbc = new EstadoJdbcImplementacion();
        estadoJdbc.findAll().stream().forEach(System.out::println);
    }
    public Estado getEstadoById() {
        if (isListEmpty()) {
            System.out.println("*** No hay estados registrados.");
            return null;
        }
        while (true) {
            System.out.print("*** Ingrese el ID del estado: ");
            int id = ReadUtil.readInt();
            Estado estado = list.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (estado != null) {
                return estado;
            }
            System.out.println("*** ID incorrecto, inténtelo nuevamente.");
        }



}}
