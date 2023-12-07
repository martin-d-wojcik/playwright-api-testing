package org.example.test;

import com.microsoft.playwright.*;
import org.example.RESTservice.ResponseHandler;
import org.example.model.ActorModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActorApiTest {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext browserContext;
    private static String baseUrl;
    private static APIRequestContext apiRequestContext;
    private static APIResponse response;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        baseUrl = "http:/localhost:8080/api/v1";
    }

    @BeforeEach
    public void initialiseContext() {
        //Below lines of code will enable tracing
        browserContext = browser.newContext();
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @Test
    public void testGetAllActorsShouldReturnArray() {
        // Prepare
        apiRequestContext = browserContext.request();

        // Perform
        response = apiRequestContext.get(baseUrl + "/actors");

        // Assert
        assertEquals(response.status(), 200);
        assertTrue(ResponseHandler.arrayInResponseHasValues(response));
    }

    @Test
    public void testGetActorByIdShouldPass() {
        // Prepare
        apiRequestContext = browserContext.request();
        long actorId = 2L;

        // Perform
        response = apiRequestContext.get(baseUrl + "/actor/id/" + actorId);

        // Assert
        assertEquals(response.status(), 200);

        // Deserialise response to actor object
        ActorModel actorModel = ResponseHandler.deserialiseResponseToActorModelObject(response);
        assertEquals("Jason", actorModel.getFirstName());
        assertEquals("Statham", actorModel.getLastName());
        assertEquals("UK", actorModel.getBirthCountry());
    }
}
