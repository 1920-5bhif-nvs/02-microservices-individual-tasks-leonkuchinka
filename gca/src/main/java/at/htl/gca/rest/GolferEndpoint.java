package at.htl.gca.rest;

import at.htl.gca.model.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("golfer")
public class GolferEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findAll() {
        return Response.ok(em.createNamedQuery("Golfer.findall", Golfer.class).getResultList()).build();
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response find(@PathParam("id") long id) {
        Golfer g = em.find(Golfer.class, id);
        if (g != null) {
            return Response.ok(g).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") long id) {
        try {
            Golfer g = em.find(Golfer.class, id);
            List<TeeTime> teetimes = em.createQuery("select t from TeeTime t", TeeTime.class).getResultList();
            for (TeeTime t : teetimes) {
                t.removePlayer(g);
            }
            em.remove(g);
        } catch (Exception e) {
            return Response.status(404).build();
        }
        return Response.ok().build();
    }
}
