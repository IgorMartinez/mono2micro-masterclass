package br.com.igormartinez.flight;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("flights")
public class FlightResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> findAll() {
        return Flight.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findById(@PathParam("id") long id) {
        return Flight.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Flight create(Flight flight) {
        flight.id = null;
        flight.persist();

        return flight;
    }
}
