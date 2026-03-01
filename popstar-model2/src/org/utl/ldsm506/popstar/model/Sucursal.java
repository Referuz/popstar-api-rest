

package org.utl.ldsm506.popstar.model;

/**
 * @author Referuz
 */

public class Sucursal {
    private String domicilio, plazaComercial;
    private int idSucursal;
    private double latitud, longitud;

    public Sucursal() {
    }

    public Sucursal(String domicilio, String plazaComercial, int idSucursal, double latitud, double longitud) {
        this.domicilio = domicilio;
        this.plazaComercial = plazaComercial;
        this.idSucursal = idSucursal;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPlazaComercial() {
        return plazaComercial;
    }

    public void setPlazaComercial(String plazaComercial) {
        this.plazaComercial = plazaComercial;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "\"---------------------------------------"+
                "\nID: "+idSucursal+
                "\nDomicilio: "+domicilio+
                "\nPlaza: "+plazaComercial+
                "\nLatitud: "+latitud+
                "\nLongiud: "+longitud;
    }
    
    
    
}
