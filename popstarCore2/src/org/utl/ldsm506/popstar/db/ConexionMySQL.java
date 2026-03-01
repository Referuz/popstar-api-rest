/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm506.popstar.db;

/**
 * @author Referuz
 */
import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionMySQL {
    Connection conn = null;
    
   public Connection open(){
       String user = "root";
       String password ="root";
       String url = "jdbc:mysql://127.0.0.1:3306/db_popstar?useSSL=false&useUnicode=true&characterEnconding=utf-8";
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user, password);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
   }
   
    public void close(){
        try {
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Excepción controlada");
        }
    }
}
