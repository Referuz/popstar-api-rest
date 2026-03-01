
package org.utl.ldsm506.popstar.db;

import java.util.ArrayList;
import java.util.List;
import org.utl.ldsm506.popstar.model.Empleado;
import org.utl.ldsm506.popstar.model.Sucursal;

/**
 * @author Referuz
 */

public class DataPopStar {
    public List<Sucursal> buildSuc(){
        Sucursal s1 = new Sucursal("Blvd. Adolfo López Mateos 2013, Los Saldos, 37170 León","Sucursal Insurgentes", 1, 21.1495, -101.7088);
        Sucursal s2 = new Sucursal("Calle Francisco I. Madero 802, Centro, 37000 León","Plaza de La Calzada", 2, 21.1215, -101.6742);
        Sucursal s3 = new Sucursal("Zona Recreativa y Cultural (Instalaciones de la Feria), 37500 León de los Aldama, Gto., México","Feria", 3, 21.1138405, -101.6565569);
        Sucursal s4 = new Sucursal("Calle ficticia 000, Fueras, 12345 León","Utl", 4, 21.065138844935014, -101.58143865110053);
        
        List<Sucursal> sucursales = new ArrayList<>();
        sucursales.add(s1);
        sucursales.add(s2);
        sucursales.add(s3);
        sucursales.add(s4);
        return sucursales;
    }
    
    public List<Empleado> buildEmp(){
        List<Sucursal>  sucs = buildSuc();
        Empleado e1 = new Empleado(1, "Sebastian", "Torres Araujo","4771221231", sucs.get(0));
        Empleado e2 = new Empleado(2, "Estrella", "Yebra Sanches", "4777894562", sucs.get(1));
        Empleado e3 = new Empleado(3, "Romina", "Ramires Hernandez", "4778951596", sucs.get(1));
        Empleado e4 = new Empleado(4, "Said", "Rodriguez Gonzales", "4779637415", sucs.get(0));
        Empleado e5 = new Empleado(10, "Oscar", "Villanueva Gimenes", "4776549632", sucs.get(1));
        
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(e1);
        empleados.add(e2);
        empleados.add(e3);
        empleados.add(e4);
        empleados.add(e5);
        return empleados;
    }
}
