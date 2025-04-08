package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Disquera;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImplementacion extends Conexion implements GenericoJdbc<Disquera> {
    @Override
    public List<Disquera> findAll() {
        Disquera disquera = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM TBL_Disquera";
        List<Disquera> list = null;

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
                disquera = new Disquera();
                disquera.setId(resultSet.getInt(1));
                disquera.setDisquera(resultSet.getString(2));
                list.add(disquera);
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
