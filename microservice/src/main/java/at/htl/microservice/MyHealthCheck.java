package at.htl.microservice;

import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Set;

@Liveness
@ApplicationScoped
public class MyHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    GolferService golferService;

    @PostConstruct
    public void initClient() {
    }

    @Override
    public HealthCheckResponse call(){
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("GCA Server Status");
        try{
            golferService.getGolfer();
            responseBuilder.up();
        } catch (Exception e){
            responseBuilder.down();
        }
        return responseBuilder.build();
    }
}
