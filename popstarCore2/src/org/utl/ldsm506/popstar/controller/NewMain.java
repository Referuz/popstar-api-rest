


package org.utl.ldsm506.popstar.controller;

import java.util.List;
import org.utl.ldsm506.popstar.model.Cliente;
import org.utl.ldsm506.popstar.model.Empleado;
import org.utl.ldsm506.popstar.model.Individuo;
import org.utl.ldsm506.popstar.model.Sucursal;

/**
 *
 * @author Referuz
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    
    public static void insertarCliente() {
        // TODO code application logic here
        ControllerCliente cc = new ControllerCliente();
        ControllerSucursal cs = new ControllerSucursal();
        Individuo i = new Individuo(); 
        Cliente c = new Cliente();
        Sucursal s = new Sucursal();
        
        List<Sucursal> sucursales = cs.getAll();
        
        // NUEVA PERSONA
        i.setNombre("Ma.");
        i.setApellidos("de la Luz Infante Tovar");
        i.setTelefono("0000000000");
        
        // LLENA LA SUCURSAL CON LOS DATOS DE 
        // LA SUCURSAL 3 DE LA BASE DE DATOS
        for(Sucursal sList: sucursales){
            if(sList.getIdSucursal() == 3){ // 2 = id_sucursal existente en DB
                s.setIdSucursal(sList.getIdSucursal());
                s.setDomicilio(sList.getDomicilio());
                s.setPlazaComercial(sList.getPlazaComercial());
                s.setLatitud(sList.getLatitud());
                s.setLongitud(sList.getLongitud());
            }
        }
        
        c.setIndividuo(i); // AL CLIENTE LE PONEMOS LA PERSONA
        c.setSucursal(s); // AL CLIENTE LE PONEMOS LA NUEVA SUCURSAL
        c.setDomicilio("Atargea #205, Arboledas de los lopez"); // NUEVA**
        
        System.out.println("El nuevo cliente es: "+cc.insertClient(c));
        
    }
    
    public static void eliminarCliente() {
        ControllerCliente cc = new ControllerCliente();
        Cliente c = new Cliente();
        c.setCuenta("jos00014315"); // depende de la cuenta de la DB
        cc.deleteClient(c);
    }
    
    public static void mostrarClientes() {
        ControllerCliente cc = new ControllerCliente();
        List<Cliente> clientes = cc.getAll();
        System.out.println(clientes);
    }
    
    public static void insertarEmpleado() {
        // TODO code application logic here
        ControllerEmpleado ce = new ControllerEmpleado();
        ControllerSucursal cs = new ControllerSucursal();
        Individuo i = new Individuo(); 
        Empleado e = new Empleado();
        Sucursal s = new Sucursal();
        
        List<Sucursal> sucursales = cs.getAll();
        
        // NUEVA PERSONA
        i.setNombre("Juan Santiago");
        i.setApellidos("Torres Infante");
        i.setTelefono("4771234120");
        
        // LLENA LA SUCURSAL CON LOS DATOS DE 
        // LA SUCURSAL 3 DE LA BASE DE DATOS
        for(Sucursal sList: sucursales){
            if(sList.getIdSucursal() == 3){ // 2 = id_sucursal existente en DB
                s.setIdSucursal(sList.getIdSucursal());
                s.setDomicilio(sList.getDomicilio());
                s.setPlazaComercial(sList.getPlazaComercial());
                s.setLatitud(sList.getLatitud());
                s.setLongitud(sList.getLongitud());
            }
        }
        
        e.setIndividuo(i); // AL CLIENTE LE PONEMOS LA PERSONA
        e.setSucursal(s); // AL CLIENTE LE PONEMOS LA NUEVA SUCURSAL
        e.setUsuario("Sant3714");
        e.setEmail("santiago12@mail.com");
        e.setContrasenia("allwaysWorking");
        
        System.out.println("El nuevo cliente es: "+ce.insertEmpleado(e));
        
    }
    
    public static void eliminarEmpleado(){
        Empleado e = new Empleado();
        ControllerEmpleado ce = new ControllerEmpleado();
        e.setUsuario("Sant3714");
        ce.deleteEmpleado(e);
    }
    
    public static void mostrarEmpelados(){
        ControllerEmpleado ce = new ControllerEmpleado();
        List<Empleado> empleados = ce.getAll();
        System.out.println(empleados);
    }
    
    public static void insertarSucursal(){
        ControllerSucursal cs = new ControllerSucursal();
        Sucursal s = new Sucursal();
        s.setDomicilio("Domicilio UTL prueba inserción");
        s.setLatitud(100.100);
        s.setLongitud(-50.12345);
        s.setPlazaComercial("Plaza insertada desde main");
        try {
            System.out.println(cs.insert(s));
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
        }
    }
    
    public static void main(String[] args) {        
        insertarSucursal();
    }
    
}
