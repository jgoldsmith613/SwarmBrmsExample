package com.rhc.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;

import com.rhc.model.RulesContainerDescription;
import com.rhc.model.Transaction;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RulesSteps {

	public static final String ROOT_URL = "http://localhost:8080/rest/rules/container/";

	private Transaction transaction;
	private Transaction response;

	private boolean init = false;

	@Before
	public void init() {

		if (!init) {
			Client client = ClientBuilder.newClient();
			WebTarget routing = client.target(ROOT_URL);
			RulesContainerDescription description = new RulesContainerDescription();
			description.setGroupId("com.rhc.swarm");
			description.setArtifactId("approvalRules");
			description.setVersion("0.0.6-SNAPSHOT");
			description.setName("approval");
			Entity<RulesContainerDescription> entity = Entity.json(description);
			Response post = routing.request().header("Content-Type", MediaType.APPLICATION_JSON).post(entity);
			assertTrue(post.getStatus() == 201);
			post.close();
			description.setGroupId("com.rhc.swarm");
			description.setArtifactId("routingRules");
			description.setVersion("0.0.6-SNAPSHOT");
			description.setName("routing");
			entity = Entity.json(description);
			post = routing.request().header("Content-Type", MediaType.APPLICATION_JSON).post(entity);
			assertTrue(post.getStatus() == 201);
			post.close();
		}
		init = true;
	}

	@Given("^the following transaction:$")
	public void the_following_transaction(DataTable examplesTable) throws Throwable {
		transaction = new Transaction();
		List<Transaction> transactions = examplesTable.asList(Transaction.class);
		assertTrue(transactions.size() == 1);
		transaction = transactions.get(0);
	}

	@When("^I execute approval and routing rules$")
	public void i_execute_approval_and_routing_rules() throws Throwable {
		Client client = ClientBuilder.newClient();

		WebTarget routing = client.target(ROOT_URL + "routing");
		Entity<Transaction> entity = Entity.json(transaction);
		Response routingPost = routing.request().header("Content-Type", MediaType.APPLICATION_JSON).post(entity);
		assertTrue(routingPost.getStatus() == 200);
		response = routingPost.readEntity(Transaction.class);

		WebTarget approval = client.target(ROOT_URL + "approval");
		entity = Entity.json(response);
		Response approvalPost = approval.request().header("Content-Type", MediaType.APPLICATION_JSON).post(entity);
		assertTrue(approvalPost.getStatus() == 200);
		response = approvalPost.readEntity(Transaction.class);
	}

	@Then("^it is routed to (.*)$")
	public void it_is_routed_to(String result) throws Throwable {
		assertTrue(response.getRouteTo().equals(result));
	}

	@Then("^it is approved$")
	public void it_is_approved() throws Throwable {
		assertTrue(response.getApproved());
	}

	@Then("^it is regected$")
	public void it_is_regected() throws Throwable {
		assertFalse(response.getApproved());
	}

}
