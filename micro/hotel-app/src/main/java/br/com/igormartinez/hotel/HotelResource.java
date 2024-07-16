package br.com.igormartinez.hotel;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("hotels")
public class HotelResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> findAll() {
        return Hotel.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findById(@PathParam("id") long id) {
        return Hotel.findById(id);
    }

    @GET
    @Path("/travel-order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findByTravelOrderId(@PathParam("id") long id) {
        return Hotel.findByTravelOrderId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Hotel create(Hotel hotel) {
        hotel.id = null;
        hotel.persist();

        return hotel;
    }
}
