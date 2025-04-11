package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Consola.Usuario.ColoniaCatalogo;
import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Colonia;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColoniaJdbcImplementacion extends Conexion implements GenericoJdbc<Colonia> {
    private static ColoniaJdbcImplementacion coloniaJdbc;

    private ColoniaJdbcImplementacion()
    {
        super();
    }

    public static ColoniaJdbcImplementacion getInstance()
    {
        if(coloniaJdbc==null)
        {
            coloniaJdbc = new ColoniaJdbcImplementacion();
        }
        return coloniaJdbc;
    }

    @Override
    public List<Colonia> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Colonia> list = null;
        Colonia colonia = null;
        String sql ="SELECT tbl_colonia.*, tbl_municipio.NOMBRE AS MUNICIPIO, tbl_estado.NOMBRE AS ESTADO " +
                "FROM tbl_colonia " +
                "INNER JOIN tbl_municipio ON tbl_municipio.id = tbl_colonia.tbl_municipio_id " +
                "INNER JOIN tbl_estado ON tbl_estado.id = tbl_municipio.tbl_estado_id;";

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
                colonia = new Colonia();
                colonia.setId( resultSet.getInt( "ID" ) );
                colonia.setNombre( resultSet.getString( "NOMBRE" ) );
                colonia.setCp( resultSet.getString( "CP" ) );

                Municipio municipio = new Municipio();
                municipio.setId( resultSet.getInt("TBL_MUNICIPIO_ID") );
                municipio.setNombre( resultSet.getString("MUNICIPIO") );
                municipio.setEstado( new Estado(resultSet.getString("ESTADO")) );

                colonia.setMunicipio( municipio );

                list.add( colonia );
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
    public boolean save(Colonia colonia)
    {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO tbl_colonia (NOMBRE, CP, TBL_MUNICIPIO_ID) VALUES (?,?,?)";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, colonia.getNombre());
            preparedStatement.setString(2, colonia.getCp());
            preparedStatement.setInt(3, colonia.getMunicipio().getId());

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
    public boolean update(Colonia colonia)
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE tbl_colonia SET NOMBRE = ?, CP = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, colonia.getNombre());
            preparedStatement.setString( 2,colonia.getCp() );
            preparedStatement.setInt(3, colonia.getId());

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
    public boolean delete(Colonia colonia)
    {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM tbl_colonia WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("> Error de conexión.");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, colonia.getId());

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
    public Colonia findById(Integer id)
    {
        Colonia colonia = null;
        String query = "SELECT tbl_colonia.*, tbl_municipio.NOMBRE as MUNICIPIO, tbl_estado.NOMBRE as ESTADO FROM tbl_colonia " +
                "INNER JOIN tbl_municipio ON tbl_municipio.id = tbl_colonia.tbl_municipio_id " +
                "INNER JOIN tbl_estado ON tbl_estado.id = tbl_municipio.tbl_estado_id " +
                "WHERE tbl_colonia.ID = ?;";
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
                colonia = new Colonia();
                colonia.setId(resultSet.getInt( "ID" ));
                colonia.setNombre(resultSet.getString( "NOMBRE" ));
                colonia.setCp(resultSet.getString("CP") );

                Municipio municipio = new Municipio();
                municipio.setId( resultSet.getInt("TBL_MUNICIPIO_ID") );
                municipio.setNombre( resultSet.getString("MUNICIPIO") );
                municipio.setEstado( new Estado(resultSet.getString("ESTADO")) );

                colonia.setMunicipio( municipio );
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return colonia;
    }

    public List<Colonia> findByMunicipioId(int municipioId)
    {
        String query = "SELECT ID, NOMBRE FROM tbl_colonia WHERE TBL_MUNICIPIO_ID = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Colonia> list = new ArrayList<>();
        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, municipioId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Colonia colonia = new Colonia();
                colonia.setId(resultSet.getInt("ID"));
                colonia.setNombre(resultSet.getString("NOMBRE"));
                list.add(colonia);
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}