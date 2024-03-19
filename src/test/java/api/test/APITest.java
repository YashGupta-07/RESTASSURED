package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import api.endpoints.ReqResEndPoints;
import api.payload.ReqRes;
import io.restassured.response.Response;

public class APITest {
	
	Faker faker;
	ReqRes ReqResPayload;
	int id;
	
	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		ReqResPayload = new ReqRes();

		ReqResPayload.setName(faker.name().username());
		ReqResPayload.setJob(faker.job().title());
		//ReqResPayload.setLastName(faker.name().lastName());
		
		logger = LogManager.getLogger(this.getClass());
	}


	@Test(priority = 1)

	public void testPostUser() {
		logger.info("******Creating User******");
		Response response = ReqResEndPoints.createUser(ReqResPayload);
		response.then().log().all();
		//id = response.body().jsonPath().getInt("id");

		Assert.assertEquals(response.getStatusCode(), 201);
		logger.info("******User is created******");
	}

	//@Test(priority = 2)

	public void testGetUserByName() {
		logger.info("******Reading User info******");
		Response response = ReqResEndPoints.readUser(this.ReqResPayload.getId());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******User info is displayed******");
	}
	

	@Test(priority = 3)

	public void testUpdateUserByName() {
		
		logger.info("******Updating User******");
		ReqResPayload.setName(faker.name().firstName());
		ReqResPayload.setJob(faker.job().title());
		
		Response response = ReqResEndPoints.updateUser(this.ReqResPayload.getName(),ReqResPayload);
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
		//Checking data after update
//		Response responseAfterUpdate = ReqResEndPoints.readUser(this.ReqResPayload.getId());
//
//		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("******User is updated******");
		
	}
	
	@Test(priority = 4)

	public void testDeleteUserByName() {
		logger.info("******Deleting User******");
		Response response = ReqResEndPoints.deleteUser(this.ReqResPayload.getName());
		Assert.assertEquals(response.getStatusCode(), 204);
		logger.info("******User Deleted******");
	}
}
