package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Artista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistaJdbcImplementacion extends Conexion implements GenericoJdbc<Artista> {
    @Override
    public List<Artista> findAll() {
        Artista artista = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM TBL_Artista";
        List<Artista> list = null;

        try
        {
            connection = getConnection();
            if(connection == null){
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet == null){
                return null;
            }
            list = new ArrayList<>();
            while(resultSet.next()){
                artista = new Artista();
                artista.setId(resultSet.getInt(1));
                artista.setArtista(resultSet.getString(2));
                list.add(artista);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
