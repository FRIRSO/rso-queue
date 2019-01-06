package si.fri.rso.projekt.queue.api.v1.resources;

import org.json.JSONObject;
import si.fri.rso.projekt.queue.services.beans.QueueBean;
import si.fri.rso.projekt.queue.models.Queue;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("queues")
public class QueueApi {

    @Inject
    private QueueBean queueBean;


    //@GET
    //@Path("url")
    //public Response test() {
    //    return Response.status(Response.Status.OK).entity(queueBean.getMessageDiscovery()).build();
    //
    //}

    @GET
    @Path("url2")
    public Response test2() {
        return Response.status(Response.Status.OK).entity(queueBean.getMessageDiscovery2()).build();

    }

    @GET
    @Path("service")
    public Response service() {
        return Response.status(Response.Status.OK).entity(queueBean.readConfig()).build();
    }

    @GET
    @Path("disable")
    public Response test4() {
        queueBean.setConfig(false);
        String response = "OK";
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("enable")
    public Response test5() {
        queueBean.setConfig(true);
        String response = "OK";
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQueues() {
        return Response.ok(queueBean.getQueues()).build();
    }

    @GET
    @Path("/{delivererID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQueuesbyDelivererID(@PathParam("delivererID") Integer delivererID) {
        List<Queue> queue = queueBean.getQueue(delivererID);

        if(queue != null) {
            return Response.ok(queue).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQueue(String queue) {
        queueBean.createQueue(new JSONObject(queue));

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/delete/{queueID}")
    public Response deleteBuyer(@PathParam("queueID") Integer queueID) {
        queueBean.deleteQueue(queueID);

        return Response.status(Response.Status.OK).build();
    }
}
