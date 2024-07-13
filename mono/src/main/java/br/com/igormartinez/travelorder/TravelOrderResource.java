package br.com.igormartinez.travelorder;

import java.util.List;

import br.com.igormartinez.flight.Flight;
import br.com.igormartinez.flight.FlightResource;
import br.com.igormartinez.hotel.Hotel;
import br.com.igormartinez.hotel.HotelResource;
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

    private FlightResource flightResource;
    private HotelResource hotelResource;

    public TravelOrderResource(FlightResource flightResource, HotelResource hotelResource) {
        this.flightResource = flightResource;
        this.hotelResource = hotelResource;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> findAll() {
        return TravelOrder.<TravelOrder>listAll()
            .stream()
            .map(
                order -> TravelOrderDTO.of(
                    order, 
                    flightResource.findByTravelOrderId(order.id), 
                    hotelResource.findByTravelOrderId(order.id)
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
        flight.travelOrderId = order.id;
        flight.fromAirport = orderDTO.getFromAirport();
        flight.toAirport = orderDTO.getToAirport();
        flightResource.create(flight);
        
        Hotel hotel = new Hotel();
        hotel.travelOrderId = order.id;
        hotel.nights = orderDTO.getNights();
        hotelResource.create(hotel);

        return order; 
    }
}
