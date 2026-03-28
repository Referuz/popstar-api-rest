
package org.utl.ldsm506.popstar.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.ldsm506.popstar.controller.ControllerEmpleado;
import org.utl.ldsm506.popstar.model.Empleado;

/**
 * @author Referuz
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
    
    @Path("agregarEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response agregarEmpleado(@FormParam("datosEmpleado") @DefaultValue("") 
        String datosEmpleado){
        String out = """
                     {"prueba": "Proximamente una insercion"}
                     """;
        ControllerEmpleado cc = new ControllerEmpleado();
        Empleado empleado = null;
        Gson gson = new Gson();
        try {
            empleado = cc.insertEmpleado(gson.fromJson(datosEmpleado, Empleado.class));
            //            insertEmpleado
            out = new Gson().toJson(empleado);
        } catch (JsonSyntaxException je) {
            out = """
                     {"error": "No se pudo transformar el ducumento JSON"}
                     """;
        } catch(Exception e){
            out = """
                     {"error": "No se pudo acceder al servidor"}
                     """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    
    @Path("eliminarEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response deleteEmpleado(@FormParam("datosEmpleado") @DefaultValue("")
        String datosEmpleado){
        ControllerEmpleado cc = new ControllerEmpleado();
        Gson gson = new Gson();
        String out = "";
        try {
            cc.deleteEmpleado(gson.fromJson(datosEmpleado, Empleado.class));
            out = """
                     {"response": "deleted"}
                     """;
        } catch (Exception e) {
            out = """
                     {"response": "not_deleted"}
                     """;
            e.printStackTrace();
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("actualizarEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response updateEmpleado(@FormParam("datosEmpleado") @DefaultValue("")
        String datosEmpleado){
        ControllerEmpleado cc = new ControllerEmpleado();
        Gson gson = new Gson();
        String out = "";
        try {
            cc.updateEmpleado(gson.fromJson(datosEmpleado, Empleado.class));
            out = """
                    {"response": "updated"}
                    """;
                 //out = new Gson().toJson(datosEmpleado);
        } catch(JsonParseException jpe){
            out = """
                     {"response": "bad json"}
                     """;
            jpe.printStackTrace();
            
        } catch (Exception e) {
            out = """
                     {"response": "not_updated"}
                     """;
            e.printStackTrace();
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
