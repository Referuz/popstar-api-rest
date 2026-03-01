
package org.utl.ldsm506.popstar.controller;

import java.util.ArrayList;
import java.util.List;
import org.utl.ldsm506.popstar.db.ConexionMySQL;
import org.utl.ldsm506.popstar.model.Empleado;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.utl.ldsm506.popstar.model.Sucursal;


/**
 * @author Referuz
 */

public class ControllerEmpleado {
    
    List<Empleado> empleados = new ArrayList<>();
    
    public List<Empleado> getAll(){
        ConexionMySQL dbConection = new ConexionMySQL();
        String query = "SELECT * FROM v_empleados;";
        try {
            Connection conn = dbConection.open();
            PreparedStatement pstm  = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Sucursal s = new Sucursal();
                Empleado e = new Empleado();
                s.setDomicilio(rs.getString("domicilio"));
                s.setIdSucursal(rs.getInt("id_sucursal"));
                s.setLatitud(rs.getDouble("latitud"));
                s.setLongitud(rs.getDouble("longitud"));
                s.setPlazaComercial(rs.getString("plaza_comercial"));
                
                e.setApellidos(rs.getString("apellidos"));
                e.setIdSucursal(s);
                e.setNombre(rs.getString("nombre"));
                e.setNumeroEmpleado(rs.getInt("numero_empleado"));
                e.setTelefono(rs.getString("telefono"));
                empleados.add(e);
            }
            rs.close();
            pstm.close();
            dbConection.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
        
        return empleados;
    }
    
    public void insert(Empleado e){
        
    }
    
    public void delete(Empleado e){
        
    }
    
    public void update(Empleado e){
        
    }
}
