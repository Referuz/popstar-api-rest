
package org.utl.ldsm506.popstar.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import org.utl.ldsm506.popstar.db.ConexionMySQL;
import org.utl.ldsm506.popstar.db.DataPopStar;
import org.utl.ldsm506.popstar.model.Cliente;
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

    public Sucursal insert(Sucursal s){
        ConexionMySQL con = new ConexionMySQL();
        Connection conn = null;
        CallableStatement cls = null;
        String consulta = "{call sp_insert_sucursal(?, ?, ?, ?, ?)};";
        try {
            conn = con.open();
            cls = conn.prepareCall(consulta);
            cls.setString(1, s.getDomicilio());
            cls.setString(2, s.getPlazaComercial());
            cls.setDouble(3, s.getLatitud());
            cls.setDouble(4, s.getLongitud());
            cls.registerOutParameter(5, Types.INTEGER);
            cls.execute();
            s.setIdSucursal(cls.getInt(5));
            s.setEstado(0);
        } catch (Exception e) {
        
        }
        
        return s;

    }

    public void deleteSucursal(Sucursal s){
        ConexionMySQL con = new ConexionMySQL();
        Connection conn = null;
        CallableStatement cls = null;
        String sql = "{CALL sp_deleteSucursal(?)};";
        try {
            conn = con.open();
            cls = conn.prepareCall(sql);
            // Envio de query para un procedure
            // Envio de parametros de entrada
            cls.setInt(1, s.getIdSucursal());
            // Ejecutar la consulta
            cls.execute();
            cls.close();
            conn.close();
            con.close();
            System.out.println("Eliminación finalizada");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
 
}
