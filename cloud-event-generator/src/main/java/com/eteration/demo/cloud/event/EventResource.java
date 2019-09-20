package com.eteration.demo.cloud.event;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {


    
    @Inject 
    protected EventGenerator eventGenerator;




    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String root() {
        return   "Total Event count =>"+eventGenerator.get();
    }

  

   
}