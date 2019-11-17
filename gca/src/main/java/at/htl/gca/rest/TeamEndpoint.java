package at.htl.gca.rest;

import at.htl.gca.model.Team;
import at.htl.gca.model.TeamPlayer;
import at.htl.gca.model.TeeTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("team")
public class TeamEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findall(){
        List<Team> list = em.createNamedQuery("Team.findall", Team.class).getResultList();
        if(list == null || list.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(list).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") long id){
        Team t = em.find(Team.class, id);
        if(t == null)
            return Response.noContent().build();
        return Response.ok(t).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Team t){
        em.persist(t);
        em.flush();
        return Response.created(URI.create("http://localhost:8080/api/team/" + t.getId())).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id,  Team t){
        Team team = em.find(Team.class, id);
        team.setTeamName(t.getTeamName());
        em.merge(team);
        return Response.ok().build();
    }

    @Path("{id}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("id") long id){
        try{
            Team t = em.find(Team.class, id);
            if(t != null){
                List<TeamPlayer> players = em
                        .createQuery("select t from TeamPlayer t where t.team = :arg", TeamPlayer.class)
                            .setParameter("arg", t)
                            .getResultList();
                for (TeamPlayer p:players) {
                    p.setTeam(null);
                }
                em.remove(t);
            }
        }catch (Exception e){
            return Response.status(404).build();
        }
        return Response.ok().build();
    }

}
