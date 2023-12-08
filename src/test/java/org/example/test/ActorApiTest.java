package org.example.test;

import com.microsoft.playwright.*;
import org.example.RESTservice.RequestHandler;
import org.example.RESTservice.ResponseHandler;
import org.example.model.ActorModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActorApiTest {
    private static String baseUrl;
    private static APIResponse response;
    private static ActorModel actorModel;
    private static RequestHandler requestHandler;

    @BeforeAll
    static void setup() {
        requestHandler = new RequestHandler();
        baseUrl = "http:/localhost:8080/api/v1";
    }

    @Test
    public void testGetActorByNameShouldPass() {
        // Prepare
        String actorFirstName = "Jason";

        // Perform
        response = requestHandler.getRequest(baseUrl + "/actor/" + actorFirstName);

        // Assert
        assertEquals(200, response.status());
        assertTrue(ResponseHandler.arrayHasActors(response, actorFirstName));
    }

    @Test
    public void testAddNewActorShouldPass() {
        // Prepare
        actorModel = new ActorModel(103L,
                "Roy",
                "Lling",
                "USA",
                1L);

        // Perform
        response = requestHandler.postRequest(baseUrl + "/actor", actorModel);

        // Assert
        assertEquals(201, response.status());
        assertTrue(response.text().contains("Created"));
        assertTrue(response.text().contains(actorModel.getFirstName()));
        assertTrue(response.text().contains(actorModel.getLastName()));
    }

    @Test
    public void testGetActorByIdShouldPass() {
        // Prepare
        long actorId = 2L;

        // Perform
        response = requestHandler.getRequest(baseUrl + "/actor/id/" + actorId);

        // Assert
        assertEquals(200, response.status());
        actorModel = ResponseHandler.deserialiseResponseToActorModelObject(response);
        assertEquals("Jason", actorModel.getFirstName());
        assertEquals("Statham", actorModel.getLastName());
        assertEquals("UK", actorModel.getBirthCountry());
    }

    @Test
    public void testGetAllActorsShouldReturnArray() {
        // Perform
        response = requestHandler.getRequest(baseUrl + "/actors");

        // Assert
        assertEquals(200, response.status());
        assertTrue(ResponseHandler.arrayInResponseHasValues(response));
    }

    @Test
    public void testUpdateActorShouldPass() {
        // Prepare
        long actorId = 102L;
        ActorModel updateActor = new ActorModel(actorId,
                "Les",
                "Alone",
                "USA",
                1L);

        // Perform
        response = requestHandler.putRequestActor(baseUrl + "/actor/" + actorId, updateActor);

        // Assert
        assertEquals(200, response.status());
        assertTrue(response.text().contains("Updated"));
    }

    @Test
    public void testDeleteActorByIdShouldPass() {
        // Prepare
        long actorId = 102L;

        // Perform
        response = requestHandler.deleteRequest(baseUrl + "/actor/" + actorId);

        // Assert
        assertEquals(200, response.status());
        assertTrue(response.text().contains("Deleted"));
    }
}
