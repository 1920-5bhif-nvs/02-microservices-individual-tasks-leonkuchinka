package at.htl.microservice.golfer.boundary;


import at.htl.microservice.golfer.entity.Golfer;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/golfer")
@RegisterRestClient
public interface GolferService {

    @GET
    @Produces("application/json")
    List<Golfer> getGolfer();
}
