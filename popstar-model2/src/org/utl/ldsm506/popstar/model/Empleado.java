
package org.utl.ldsm506.popstar.model;

/**
 * @author Referuz
 */

public class Empleado {
    private int numeroEmpleado;
    private String usuario, contrasenia, email;
    private Sucursal sucursal;
    private Individuo individuo;

    public Empleado() {
    }

    public Empleado(int numeroEmpleado, String usuario, String contrasenia, String email, Sucursal sucursal, Individuo individuo) {
        this.numeroEmpleado = numeroEmpleado;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.sucursal = sucursal;
        this.individuo = individuo;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Individuo getIndividuo() {
        return individuo;
    }

    public void setIndividuo(Individuo individuo) {
        this.individuo = individuo;
    }

    @Override
    public String toString() {
        return "Empleado{" + "numeroEmpleado=" + numeroEmpleado + 
                ", usuario=" + usuario + ", contrasenia=" + contrasenia + 
                ", email=" + email + 
                ", sucursal=" + "{Domicilio="+ sucursal.getDomicilio() +
                                  ", Plaza="+ sucursal.getPlazaComercial() +
                                  ", [Latitud,Longitud]=["+ 
                                        sucursal.getLatitud() +","+
                                        sucursal.getLongitud() +"]" +
                ", id_individuo=" + individuo.getIdIndividuo() + '}';
    }

    
    
}
