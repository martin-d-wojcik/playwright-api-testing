package org.example.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.example.model.MovieModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieApiTest {

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
    public void testAddNewMovieShouldPass() {
        // prepare
        APIRequestContext request = context.request();
        MovieModel movie = new MovieModel(1L,
                "Lock, Stock and Two Smoking Barells",
                "Guy Rithcie",
                "Guy Rithcie",
                "Action/Comedy",
                "Funny films awards",
                1998);

        // Perform
        APIResponse apiResponse = request.post("http://localhost:8081/api/v1/movie/add",
                RequestOptions.create()
                        .setData(movie));

        // Assert
        assertEquals(apiResponse.status(), 201);
    }
}
