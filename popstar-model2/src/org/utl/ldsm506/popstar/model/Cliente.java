/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm506.popstar.model;

/**
 * @author Referuz
 */


public class Cliente {
    private String cuenta, domicilio;
    private Sucursal sucursal;
    private Individuo individuo;
    private byte status;

    public Cliente() {
    }

    public Cliente(String cuenta, String comicilio, Sucursal sucursal, Individuo individuo, byte status) {
        this.cuenta = cuenta;
        this.domicilio = comicilio;
        this.sucursal = sucursal;
        this.individuo = individuo;
        this.status = status;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String comicilio) {
        this.domicilio = comicilio;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cuenta=" + cuenta +
                ", comicilio=" + domicilio + 
                ", sucursal=" + "{Domicilio="+ sucursal.getDomicilio() +
                                  ", Plaza="+ sucursal.getPlazaComercial() +
                                  ", [Latitud,Longitud]=["+ 
                                        sucursal.getLatitud() +","+
                                        sucursal.getLongitud() +"]" + 
                ", id_individuo=" + individuo.getIdIndividuo() + 
                ", status=" + status + '}';
    }
    
    
}
