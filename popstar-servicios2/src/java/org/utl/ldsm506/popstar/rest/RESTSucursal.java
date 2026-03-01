
package org.utl.ldsm506.popstar.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.ldsm506.popstar.controller.ControllerSucursal;
import org.utl.ldsm506.popstar.model.Sucursal;

/**
 * @author Referuz
 */

@Path("sucursal")
public class RESTSucursal extends Application{
    
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll(){
        String out = null;
        ControllerSucursal cs = new ControllerSucursal();
        List<Sucursal> sucs = null;
        try {
        sucs = cs.getAll();
        out = new Gson().toJson(sucs);
        } catch (Exception e) {
            out = """
                  {"response": ["Error" : "No hay conexión a la Base de Datos]}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
