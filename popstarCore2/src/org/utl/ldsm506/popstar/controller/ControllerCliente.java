

package org.utl.ldsm506.popstar.controller;

/**
 * @author Referuz
 */
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.CallableStatement;
import org.utl.ldsm506.popstar.db.ConexionMySQL;
import org.utl.ldsm506.popstar.model.Cliente;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.ldsm506.popstar.model.Individuo;
import org.utl.ldsm506.popstar.model.Sucursal;

public class ControllerCliente {
    
    List<Cliente> clientes = new ArrayList<>();
    
    public List<Cliente> getAll(){
        ConexionMySQL con = new ConexionMySQL();
        Connection conn = null;
        String sql = "SELECT * FROM v_clientes;";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = con.open();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                Individuo i = new Individuo();
                Sucursal s = new Sucursal();
                
                i.setNombre(rs.getString("nombre"));
                i.setApellidos(rs.getString("apellidos"));
                i.setTelefono(rs.getString("telefono"));
                
                s.setPlazaComercial(rs.getString("plaza_comercial"));
                s.setDomicilio(rs.getString("domicilio_sucursal"));
                s.setLongitud(rs.getDouble("longitud"));
                s.setLatitud(rs.getDouble("latitud"));
                
                c.setCuenta(rs.getString("cuenta"));
                c.setDomicilio(rs.getString("domicilio_cliente"));
                c.setIndividuo(i);
                c.setSucursal(s);
                clientes.add(c);
            }
            
            rs.close();
            pstm.close();
            conn.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }
    
    public Cliente insertClient(Cliente c){
        ConexionMySQL con = new ConexionMySQL();
        Connection conn = null;
        CallableStatement cls = null;
        String sql = "{CALL sp_insertCliente(?, ?, ?, ?, ?, ?, ?)};";
        try {
            conn = con.open();
            cls = conn.prepareCall(sql);
            // Envio de query para un procedure
            // Envio de parametros de entrada
            cls.setString(1, c.getIndividuo().getNombre());
            cls.setString(2, c.getIndividuo().getApellidos());
            cls.setString(3, c.getIndividuo().getTelefono());
            cls.setString(4, c.getDomicilio());
            cls.setInt(5, c.getSucursal().getIdSucursal());
            // Envio de parametros de salida
            cls.registerOutParameter(6, Types.VARCHAR);
            cls.registerOutParameter(7, Types.INTEGER);
            // Ejecutar la consulta
            cls.execute();
            c.setCuenta(cls.getString(6));
            c.getIndividuo().setIdIndividuo(cls.getInt(7));
            c.setStatus(1);
            cls.close();
            conn.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public void deleteClient(Cliente c){
        ConexionMySQL con = new ConexionMySQL();
        Connection conn = null;
        CallableStatement cls = null;
        String sql = "{CALL sp_deleteCliente(?)};";
        try {
            conn = con.open();
            cls = conn.prepareCall(sql);
            // Envio de query para un procedure
            // Envio de parametros de entrada
            cls.setString(1, c.getCuenta());
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
