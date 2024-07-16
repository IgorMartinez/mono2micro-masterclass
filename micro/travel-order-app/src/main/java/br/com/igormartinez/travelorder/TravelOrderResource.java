package br.com.igormartinez.travelorder;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("travel-orders")
public class TravelOrderResource {

    @RestClient
    @Inject
    private FlightService flightService;

    @RestClient
    @Inject
    private HotelService hotelService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> findAll() {
        return TravelOrder.<TravelOrder>listAll()
            .stream()
            .map(
                order -> TravelOrderDTO.of(
                    order, 
                    flightService.findByTravelOrderId(order.id), 
                    hotelService.findByTravelOrderId(order.id)
                )
            )
            .toList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById(@PathParam("id") long id) {
        return TravelOrder.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public TravelOrder create(TravelOrderDTO orderDTO) {
        TravelOrder order = new TravelOrder();
        order.persist();

        Flight flight = new Flight();
        flight.setTravelOrderId(order.id);
        flight.setFromAirport(orderDTO.getFromAirport());
        flight.setToAirport(orderDTO.getToAirport());
        flightService.create(flight);
        
        Hotel hotel = new Hotel();
        hotel.setTravelOrderId(order.id);
        hotel.setNights(orderDTO.getNights());
        hotelService.create(hotel);

        return order; 
    }
}
