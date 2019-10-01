package at.htl.microservice;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Set;

@Path("/golfer")
@RegisterRestClient
public interface GolferService {

    @GET
    @Produces("application/json")
    List<Golfer> getGolfer();
}
