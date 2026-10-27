package org.example.tp2_micros;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PathParam;

@Path("/calcul")
public class CalculRest {

    private final Calculatrice calculService = new Calculatrice();

    @GET
    @Path("/somme")
    @Produces(MediaType.TEXT_PLAIN)
    public Response somme(@QueryParam("a") int a, @QueryParam("b") int b) {
        int result = calculService.somme(a, b);
        return Response.ok(result).build();
    }
    @GET
    @Path("/soustraction")
    @Produces(MediaType.TEXT_PLAIN)
    public int soustraction(@QueryParam("a") int a, @QueryParam("b") int b) {
        return calculService.soustraction(a, b); // Assurez-vous que la méthode existe dans votre service
    }

    @GET
    @Path("/produit")
    @Produces(MediaType.TEXT_PLAIN)
    public int produit(@QueryParam("a") int a, @QueryParam("b") int b) {
        return calculService.produit(a, b);
    }
    @GET
    @Path("/division")
    @Produces(MediaType.TEXT_PLAIN)
    public Response division(@QueryParam("a") int a, @QueryParam("b") int b) {
        if (b == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erreur : division par 0 impossible")

                    .build();

        }
        double result = calculService.division(a, b);
        return Response.ok(result).build();
    }
    @GET
    @Path("/produit/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response produitChemin(@PathParam("a") int a, @PathParam("b") int b) {
        int result = calculService.produit(a, b);
        return Response.ok(result).build();
    }
    @GET
    @Path("/somme/detail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sommeDetail(@QueryParam("a") int a, @QueryParam("b") int b) {
        Resultat resultat = new Resultat("somme", a, b, calculService.somme(a, b));
        return Response.ok(resultat).build();
    }
}