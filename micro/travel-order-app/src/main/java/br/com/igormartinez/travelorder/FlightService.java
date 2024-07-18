package br.com.igormartinez.travelorder;

import java.time.temporal.ChronoUnit;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8081/flights")
public interface FlightService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findById(@PathParam("id") long id);

    @GET
    @Path("/travel-order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallbackFindByTravelOrderId")
    @CircuitBreaker(
        requestVolumeThreshold = 4,
        failureRatio = 0.5,
        delay = 5000, 
        successThreshold = 2 
    )
    public Flight findByTravelOrderId(@PathParam("id") long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Flight create(Flight flight);

    default Flight fallbackFindByTravelOrderId(@PathParam("id") long id) {
        Flight flight = new Flight();
        flight.setTravelOrderId(id);
        flight.setFromAirport("");
        flight.setToAirport("");
        return flight;
    }
}
