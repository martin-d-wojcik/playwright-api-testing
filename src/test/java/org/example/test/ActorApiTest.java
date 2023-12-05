package org.example.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.microsoft.playwright.*;
import org.example.RESTservice.ResponseHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActorApiTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    private static String baseUrl;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        baseUrl = "http:/localhost:8080/api/v1";
    }

    @BeforeEach
    public void initialiseContext() {
        //Below lines of code will enable tracing
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @Test
    public void testGetAllActorsShouldReturnArray() {
        // Prepare
        APIRequestContext request = context.request();

        // Perform
        APIResponse response = request.get(baseUrl + "/actors");

        // Assert
        assertEquals(response.status(), 200);
        assertTrue(ResponseHandler.arrayInResponseHasValues(response));
    }

    
}
