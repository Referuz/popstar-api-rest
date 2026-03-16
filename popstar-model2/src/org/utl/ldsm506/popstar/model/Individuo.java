/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm506.popstar.model;

/**
 *
 * @author Referuz
 */
public class Individuo {
    private int idIndividuo;
    private String nombre, apellidos, telefono;

    public Individuo() {
    }

    public int getIdIndividuo() {
        return idIndividuo;
    }

    public void setIdIndividuo(int idIndividuo) {
        this.idIndividuo = idIndividuo;
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

    @Override
    public String toString() {
        return "Individuo{" + "idIndividuo=" + idIndividuo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + '}';
    }
    
    
}
