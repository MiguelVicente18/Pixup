package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.GeneroMusical;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImplementacion extends Conexion implements GenericoJdbc<GeneroMusical> {

    @Override
    public List<GeneroMusical> findAll() {
        GeneroMusical generoMusical = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM TBL_generoMusical";
        List<GeneroMusical> list = null;

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
                generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt(1));
                generoMusical.setGeneroMusical(resultSet.getString(2));
                list.add(generoMusical);
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
