package com.redhat.training.speaker;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.models.examples.Example;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;


@Path("/speakers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpeakerResource {

    

    @GET
    @Operation(summary = "Retrieves the list of speakers") 
    @APIResponse(responseCode = "200")
    public List<Speaker> getSpeakers( @QueryParam( value = "sortBy") @DefaultValue("id") String sortBy,
                                      @QueryParam( value = "pageIndex")  @DefaultValue("1") Integer pageIndex,
                                      @QueryParam( value = "pageSize")  @DefaultValue("25")Integer pageSize  
                                    ) {
        PanacheQuery<Speaker> speakerQuery = Speaker.findAll(  Sort.by( filterSortBy(sortBy) ) );                                
        return speakerQuery.page(Page.of(pageIndex - 1, pageSize)).list();
    }

    @POST
    @Transactional
    @Operation(summary = "Adds a speaker") 
    @APIResponse( responseCode = "201", 
        headers = { @Header( name = "id",
                            description = "ID of the created entity", schema = @Schema(implementation = Integer.class)

                            ), 
                    @Header( name = "location",
                             description = "URI of the created entity", schema = @Schema(implementation = String.class) 
                            ),
                  }
                , description = "Entity successfully created"
                )
    public Response createSpeaker(Speaker newSpeaker, @Context UriInfo uriInfo) {
        newSpeaker.persist();

        return Response.created(generateUriForSpeaker(newSpeaker, uriInfo))
            .header("id", newSpeaker.id)
            .build();
    }

    private URI generateUriForSpeaker(Speaker speaker, UriInfo uriInfo) {
        return uriInfo.getAbsolutePathBuilder().path("/{id}").build(speaker.id);
    }

    private String filterSortBy(String sortBy) {
        if (!sortBy.equals("id") && !sortBy.equals("name")){
            return "id";
        }

        return sortBy;
    }

    @DELETE
    @Transactional
    @Path ("/{id}")
    public List<Speaker> removeByID( @PathParam("id") Integer id) {
        if (!Speaker.deleteById( id )) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return getSpeakers( "id",1,25);
        }
    }

}