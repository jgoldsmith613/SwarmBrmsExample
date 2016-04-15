package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.rules.ContainerManager;
import com.rhc.model.RulesContainerDescription;
import com.rhc.model.Transaction;

@Path("/rules")
public class RulesEndpoint {

	private static ContainerManager manager = new ContainerManager();

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello from WildFly Swarm!").build();
	}

	@POST
	@Consumes("application/json")
	@Path("/container")
	public Response createContainer(RulesContainerDescription description) {
		manager.createContainer(description);
		return Response.status(201).build();

	}

	@PUT
	@Consumes("application/json")
	@Path("/container/{name}")
	public Response updateContainer(RulesContainerDescription description, @PathParam("name") String name) {
		manager.updateContainer(description);
		return Response.status(200).build();
	}

	@SuppressWarnings("unchecked")
	@POST
	@Produces("application/json")
	@Path("/container/{name}")
	public Response execute(@PathParam("name") String name, Transaction transaction) {
		manager.getContainer(name).newStatelessKieSession().execute(transaction);
		return Response.status(200).entity(transaction).build();
	}

}