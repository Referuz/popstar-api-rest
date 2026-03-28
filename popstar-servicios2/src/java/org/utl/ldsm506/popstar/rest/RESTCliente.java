/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm506.popstar.rest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.ldsm506.popstar.controller.ControllerCliente;
import org.utl.ldsm506.popstar.model.Cliente;

/**
 *
 * @author Referuz
 */
@Path("cliente")
public class RESTCliente extends Application{
    
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll(){
        String out = null;
        ControllerCliente cc = new ControllerCliente();
        List<Cliente> clientes = null;
        
        try {
            clientes = cc.getAll();
            out = new Gson().toJson(clientes); // []
        } catch (Exception e) {
            out = """
                  {"response": ["Error" : "No hay conexión a la Base de Datos]}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
    @Path("agregarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response agregarCliente(@FormParam("datosCliente") @DefaultValue("") 
        String datosCliente){
        String out = """
                     {"prueba": "Proximamente una insercion"}
                     """;
        ControllerCliente cc = new ControllerCliente();
        Cliente cliente = null;
        Gson gson = new Gson();
        try {
            cliente = cc.insertClient(gson.fromJson(datosCliente, Cliente.class));
            out = gson.toJson(cliente);
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

    
    @Path("eliminarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response deleteCliente(@FormParam("datosCliente") @DefaultValue("")
        String datosCliente){
        ControllerCliente cc = new ControllerCliente();
        Gson gson = new Gson();
        String out = "";
        try {
            cc.deleteClient(gson.fromJson(datosCliente, Cliente.class));
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
}
