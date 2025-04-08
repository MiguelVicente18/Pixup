package org.migueVA.Jdbc.Implementacion;

import org.migueVA.Jdbc.Conexiones.Conexion;
import org.migueVA.Jdbc.Conexiones.GenericoJdbc;
import org.migueVA.Model.Estado;
import org.migueVA.Model.Municipio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MunicipioJdbcImplementacion extends Conexion implements GenericoJdbc<Municipio> {
    @Override
    public List<Municipio> findAll() {
        Municipio municipio = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT TBL_Municipio.*,"+
                "tbl_estado.nombre as Estado" +
                "from tbl_municipio" +
                "Inner Join " +
                "tbl_estado ON tbl_municipio.tbl_estado_id = tbl_estado.id;";
        List<Municipio> list = null;

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
            while(resultSet.next())
            {
                municipio = new Municipio();
                municipio.setId(resultSet.getInt(1));
                municipio.setNombre(resultSet.getString(2));
                municipio.setEstado(new Estado(resultSet.getString(4)));
                list.add(municipio);
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
