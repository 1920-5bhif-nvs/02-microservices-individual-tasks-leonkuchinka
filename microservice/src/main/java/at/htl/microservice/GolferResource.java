package at.htl.microservice;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class GolferResource {

    @Inject
    @RestClient
    GolferService golferService;

    @GET
    @Path("/golfer/count")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getNumberOfGolfer_called" )
    @Timed(name= "timer", description = "How long it takes to perform this task", unit = MetricUnits.MILLISECONDS)
    public int getNumberOfRegisteredGolfer() {
        return golferService.getGolfer().size();
    }

    @GET
    @Path("/golfer/avg/hcp")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getAvgHcp_called" )
    public double getAvgHcp(){
        return golferService.getGolfer().stream().mapToDouble(g -> g.getHcp()).average().getAsDouble();
    }

    @GET
    @Path("/golfer/avg/age")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "getAvgAge_called" )
    public double getAvgAge(){
        return golferService.getGolfer().stream().mapToDouble(g -> g.getAge()).average().getAsDouble();
    }
}