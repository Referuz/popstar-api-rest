
package org.utl.ldsm506.popstar.model;

/**
 * @author Referuz
 */

public class Empleado {
    private int numeroEmpleado;
    private String nombre, apellidos, telefono;
    private Sucursal sucursal;

    public Empleado() {
    }

    public Empleado(int numeroEmpleado, String nombre, String apellidos, String telefono, Sucursal sucursal) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.sucursal = sucursal;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Sucursal getIdSucursal() {
        return sucursal;
    }

    public void setIdSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "\"---------------------------------------"+
                "\nNo. Empleado: "+numeroEmpleado+
                "\nNombre: "+nombre+
                "\nApellidos: "+apellidos+
                "\nTelefono: "+telefono+
                "\nIDSucursal: "+sucursal;
    }
    
    
}
