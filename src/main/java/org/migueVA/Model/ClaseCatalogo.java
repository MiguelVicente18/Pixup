package org.migueVA.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.migueVA.Util.ReadUtil;
import org.migueVA.baseDatos.impl.CatalogoDaoImpl;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class ClaseCatalogo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public abstract void alta();
    public abstract void cambio();
    public abstract void baja();
    public abstract boolean consulta();

    protected <T extends ClaseCatalogo> void realizarBaja(Class<T> tipo) {
        CatalogoDaoImpl<T> catalogoDaoImpl = new CatalogoDaoImpl<>(tipo);
        T objeto = tipo.cast(buscarCatalogo(catalogoDaoImpl));
        catalogoDaoImpl.eliminar(objeto);
        System.out.println("Elemento eliminado");
    }

    protected String realizarVista(CatalogoDaoImpl<? extends ClaseCatalogo> catalogoDao){
        List<? extends ClaseCatalogo> lista = catalogoDao.findAll();
        String cadena = "";
        for (ClaseCatalogo claseCatalogo : lista) {
            cadena += claseCatalogo;
            cadena += "\n";
        }
        return cadena;
    }

    protected ClaseCatalogo buscarCatalogo(CatalogoDaoImpl<? extends ClaseCatalogo> catalogoDao){
        ClaseCatalogo claseCatalogo = null;
        String mensaje =
                " *** Selecciona el ID del Elementnto: \n" +
                        realizarVista(catalogoDao);
        Integer id = ReadUtil.readInt(mensaje);

        claseCatalogo = catalogoDao.findById(id);

        if(claseCatalogo == null){
            System.out.println(" --> Elemento No encontrado <--");
            return null;
        } else {
            return claseCatalogo;
        }

    }


}
