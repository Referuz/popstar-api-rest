
package org.utl.ldsm506.popstar.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.ldsm506.popstar.controller.ControllerEmpleado;
import org.utl.ldsm506.popstar.model.Empleado;

/**
 * @author Torres
 */

@Path("empleado")
public class RESTEmpleado {
     @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll(){
        String out = null;
         ControllerEmpleado ce = new ControllerEmpleado();
         List<Empleado> empleados = null;
         try {
             empleados = ce.getAll();
             out = new Gson().toJson(empleados);
         } catch (Exception e) {
             
         }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
