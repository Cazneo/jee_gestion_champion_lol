package com.gestionnaire.champion_lol.ressources;

import com.gestionnaire.champion_lol.dao.ChampionDao;
import com.gestionnaire.champion_lol.model.Champion;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/champions")
public class ChampionRessource {

    private ChampionDao championDao = new ChampionDao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Champion createChampion(Champion champion) {
        return championDao.createChampion(champion);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Champion getChampion(@PathParam("id") Long id) {
        return championDao.getChampion(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Champion updateChampion(@PathParam("id") Long id, Champion champion) {
        champion.setId(id);
        return championDao.updateChampion(champion);
    }

    @DELETE
    @Path("/{id}")
    public void deleteChampion(@PathParam("id") Long id) {
        championDao.deleteChampion(id);
    }
}
