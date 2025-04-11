package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Consola.Usuario.MunicipioCatalogo;
import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;
import org.migueVA.Util.ReadUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoJdbcImplementacion extends Conexion implements GenericoJdbc<Estado> {

    private static EstadoJdbcImplementacion estadoJdbc;

    private EstadoJdbcImplementacion()
    {
        super();
    }

    public static EstadoJdbcImplementacion getInstance()
    {
        if(estadoJdbc==null)
        {
            estadoJdbc = new EstadoJdbcImplementacion();
        }
        return estadoJdbc;
    }

    @Override
    public List<Estado> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Estado> list = null;
        Estado estado = null;
        String sql ="SELECT * FROM tbl_estado";

        try
        {
            if( !openConnection() )
            {
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery( sql );

            if( resultSet == null )
            {
                return null;
            }

            list =  new ArrayList<>( );

            while( resultSet.next( ) )
            {
                estado = new Estado();
                estado.setId( resultSet.getInt( "ID" ) );
                estado.setNombre( resultSet.getString( "NOMBRE" ) );
                list.add( estado );
            }

            resultSet.close( );
            closeConnection( );

            return list;
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    @Override
    public boolean save(Estado estado)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_estado (NOMBRE) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println(" ***  Error de conexión. *** ");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estado.getNombre());

            res = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection();

            return res==1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Estado estado)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_estado SET NOMBRE = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println(" ***  Error de conexión. *** ");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, estado.getNombre());
            preparedStatement.setInt(2, estado.getId());

            res = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection();

            return res==1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Estado estado)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_estado WHERE ID = ?";
        int res = 0;
        List<Municipio> list = MunicipioJdbcImplementacion.getInstance().findByEstadoId(estado.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n *** No se puede eliminar el estado debido a los siguientes municipios asociados :  ");
            for(Municipio municipio: list)
            {
                System.out.println("- [ *** ID: "+municipio.getId()+"], [ *** NOMBRE: "+municipio.getNombre()+"]");
            }

            System.out.print(" ***  Desea eliminar también estos municipios? (Si/No): ");
            String respuesta = ReadUtil.read();

            if(!respuesta.equalsIgnoreCase("Si"))
            {
                System.out.println(" ***  Eliminación cancelada *** .");
                return false;
            }

            for(Municipio municipio: list)
            {
                if(MunicipioJdbcImplementacion.getInstance().delete(municipio))
                {
                    System.out.println(" ***  Municipios eliminados *** ");
                }
            }
        }

        try
        {
            if( !openConnection() )
            {
                System.out.println(" *** Error de conexión *** ");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, estado.getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res==1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Estado findById(Integer id)
    {
        Estado estado = null;
        String query = "SELECT * FROM tbl_estado WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                estado = new Estado();
                estado.setId(resultSet.getInt( "ID" ));
                estado.setNombre(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return estado;
    }
}
