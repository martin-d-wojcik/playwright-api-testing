package org.example.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ActorApiTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
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
    public void testGetAllActorsShouldReturnNoContentPass() throws JsonProcessingException {
        APIRequestContext request = context.request();
        APIResponse response = request.get("http://localhost:8081/api/v1/actors");
        assertEquals(response.status(), 204);
    }
}
