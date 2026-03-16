
package org.utl.ldsm506.popstar.controller;

import java.util.ArrayList;
import java.util.List;
import org.utl.ldsm506.popstar.db.ConexionMySQL;
import org.utl.ldsm506.popstar.model.Empleado;
import org.utl.ldsm506.popstar.model.Individuo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Types;
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
                Individuo i = new Individuo();
                Sucursal s = new Sucursal();
                Empleado e = new Empleado();
                
                i.setIdIndividuo(rs.getInt("id_individuo"));
                i.setNombre(rs.getString("nombre"));
                i.setApellidos(rs.getString("apellidos"));
                i.setTelefono(rs.getString("telefono"));
                
                s.setDomicilio(rs.getString("domicilio"));
                s.setIdSucursal(rs.getInt("id_sucursal"));
                s.setLatitud(rs.getDouble("latitud"));
                s.setLongitud(rs.getDouble("longitud"));
                s.setPlazaComercial(rs.getString("plaza_comercial"));
                
                e.setIndividuo(i);
                e.setNumeroEmpleado(rs.getInt("numero_empleado"));
                e.setUsuario(rs.getString("usuario"));
                e.setEmail(rs.getString("email"));
                e.setContrasenia(rs.getString("contrasenia"));
                e.setSucursal(s);
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
    
    public Empleado insertEmpleado(Empleado e){
        ConexionMySQL con = new ConexionMySQL();
        String sql = "{CALL sp_insertEmpleado(?, ?, ?, ?, ?, ?, ?, ?, ?)};";
        try {
            Connection conn = con.open();
            CallableStatement cls = conn.prepareCall(sql);
            cls.setString(1, e.getIndividuo().getNombre());
            cls.setString(2, e.getIndividuo().getApellidos());
            cls.setString(3, e.getIndividuo().getTelefono());
            cls.setInt(4, e.getSucursal().getIdSucursal());
            cls.setString(5, e.getUsuario());
            cls.setString(6, e.getContrasenia());
            cls.setString(7, e.getEmail());
            cls.registerOutParameter(8, Types.INTEGER);
            cls.registerOutParameter(9, Types.INTEGER);
            cls.execute();
            e.getIndividuo().setIdIndividuo(cls.getInt(8));
            e.setNumeroEmpleado(cls.getInt(9));
            
            cls.close();
            conn.close();
            con.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return e;
    }
    
    public void deleteEmpleado(Empleado e){
        ConexionMySQL con = new ConexionMySQL();
        String sql = "{CALL sp_deleteEmpleado(?)};";
        try {
            Connection conn = con.open();
            CallableStatement cls = conn.prepareCall(sql);
            cls.setString(1, e.getUsuario());
            cls.execute();
            
            cls.close();
            conn.close();
            con.close();
            System.out.println("Eliminación finalizada");
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
    }
}
