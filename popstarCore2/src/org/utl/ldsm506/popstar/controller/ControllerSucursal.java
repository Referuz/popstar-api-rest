
package org.utl.ldsm506.popstar.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.utl.ldsm506.popstar.db.ConexionMySQL;
import org.utl.ldsm506.popstar.db.DataPopStar;
import org.utl.ldsm506.popstar.model.Empleado;
import org.utl.ldsm506.popstar.model.Sucursal;

/**
 * @author Referuz
 */

public class ControllerSucursal {
    
    List<Sucursal> sucursales = new ArrayList<>();

    public List<Sucursal>getAll(){ 
        ConexionMySQL dbConection = new ConexionMySQL();
        String query = "SELECT * FROM v_sucursales;";
        PreparedStatement pstm  = null;
        ResultSet rs = null;
        try {
            Connection conn = dbConection.open();
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Sucursal s = new Sucursal();
                s.setDomicilio(rs.getString("domicilio"));
                s.setIdSucursal(rs.getInt("id_sucursal"));
                s.setLatitud(rs.getDouble("latitud"));
                s.setLongitud(rs.getDouble("longitud"));
                s.setPlazaComercial(rs.getString("plaza_comercial"));
                sucursales.add(s);
            }
            rs.close();
            pstm.close();
            dbConection.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
        
        return sucursales;

    }

    public void insert(Sucursal s){
        DataPopStar dps = new DataPopStar();
        sucursales=dps.buildSuc();
        sucursales.add(s);

    }

    public void delete(Sucursal s){
        DataPopStar dps = new DataPopStar();
        sucursales=dps.buildSuc();
        sucursales.remove(s);

    }

    public void update(Sucursal s){
        DataPopStar dps = new DataPopStar();
        sucursales=dps.buildSuc();
        //Implementar el metodo de busqueda de Estructura de Datos
        //Cambiar el objeto sucursal

    }
 
}
