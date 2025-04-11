package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Disco;
import org.migueVA.Model.GeneroMusical;
import org.migueVA.Util.ReadUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImplementacion extends Conexion implements GenericoJdbc<GeneroMusical> {
    private static GeneroMusicalJdbcImplementacion generoMusicalJdbc;

    private GeneroMusicalJdbcImplementacion()
    {
    }

    public static GeneroMusicalJdbcImplementacion getInstance()
    {
        if(generoMusicalJdbc==null)
        {
            generoMusicalJdbc = new GeneroMusicalJdbcImplementacion();
        }
        return generoMusicalJdbc;
    }

    @Override
    public List<GeneroMusical> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<GeneroMusical> list = null;
        GeneroMusical generoMusical = null;
        String sql ="SELECT * FROM tbl_genero_Musical";

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
                generoMusical = new GeneroMusical();
                generoMusical.setId( resultSet.getInt( "ID" ) );
                generoMusical.setGeneroMusical( resultSet.getString( "GENERO" ) );
                list.add( generoMusical );
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
    public boolean save(GeneroMusical generoMusical)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_genero_Musical (GENERO) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println(" ***  Error de conexión. *** ");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, generoMusical.getGeneroMusical());

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
    public boolean update(GeneroMusical generoMusical)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_genero_Musical SET GENERO = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println(" *** Error de conexión ***");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, generoMusical.getGeneroMusical());
            preparedStatement.setInt(2, generoMusical.getId());

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
    public boolean delete(GeneroMusical generoMusical)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_genero_Musical WHERE ID = ?";
        int res = 0;
        List<Disco> list = DiscoJdbcImplementacion.getInstance().findByGenero_MusicalId(generoMusical.getId());

        if(!list.isEmpty())
        {
            System.out.println("\n ***  No se puede eliminar el Genero Musical  porque se tiene estos discos asociados: ");
            for(Disco disco: list)
            {
                System.out.println("- [ID: "+disco.getId()+"], [TITULO: "+disco.getTituloDisco()+"]");
            }

            System.out.print(" *** Desea eliminar también estos discos? (Si/No): ");
            String respuesta = ReadUtil.read();

            if(!respuesta.equalsIgnoreCase("Si"))
            {
                System.out.println(" *** Eliminación cancelada ***.");
                return false;
            }

            for(Disco disco: list)
            {
                DiscoJdbcImplementacion.getInstance().delete(disco);
                System.out.println("> Discos eliminados.");
            }
        }

        try
        {
            if( !openConnection() )
            {
                System.out.println(" *** Error de conexión. *** ");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generoMusical.getId());

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
    public GeneroMusical findById(Integer id)
    {
        GeneroMusical generoMusical = null;
        String query = "SELECT * FROM tbl_genero_Musical WHERE ID = ?";
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
                generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt( "ID" ));
                generoMusical.setGeneroMusical(resultSet.getString( "GENERO" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return generoMusical;
    }

}
