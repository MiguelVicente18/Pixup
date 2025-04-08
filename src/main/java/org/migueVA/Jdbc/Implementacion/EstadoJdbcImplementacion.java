package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Estado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstadoJdbcImplementacion extends Conexion implements GenericoJdbc<Estado> {

    @Override
    public List<Estado> findAll() {

        Estado estado = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM tbl_estado";
        List<Estado> list = null;

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
                estado = new Estado();
                estado.setId(resultSet.getInt(1));
                estado.setNombre(resultSet.getString(2));
                list.add(estado);
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
