package org.example.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.example.RESTservice.ResponseHandler;
import org.example.model.MovieModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieApiTest {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    private static String baseUrl;
    private static APIRequestContext apiRequestContext;
    private static APIResponse response;
    private static MovieModel movieModel;

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
    public void testGetMoviebByIdShouldPass() {
        // Prepare
        apiRequestContext = browserContext.request();
        long movieId = 6L;

        // Perform
        response = apiRequestContext.get(baseUrl + "/movie/id/" + movieId);

        // Assert
        assertEquals(200, response.status());

        // Deserialise response to actor object
        movieModel = ResponseHandler.deserialiseResponseToMovieModelObject(response);
        assertEquals("The man from U.N.C.L.E", movieModel.getTitle());
        assertEquals("Guy Ritchie", movieModel.getWriter());
        assertEquals("Guy Ritchie", movieModel.getDirector());
        assertEquals("Action, Adventure, Comedy", movieModel.getGenre());
        assertEquals("SDFCS", movieModel.getAwards());
        assertEquals(2015, movieModel.getReleaseYear());
    }

    @Test
    public void testAddNewMovieShouldPass() {
        // prepare
        apiRequestContext = browserContext.request();
        movieModel = new MovieModel(1L,
                "Lock, Stock and Two Smoking Barells",
                "Guy Rithcie",
                "Guy Rithcie",
                "Action/Comedy",
                "Funny films awards",
                1998);

        // Perform
        response = apiRequestContext.post("http://localhost:8081/api/v1/movie/add",
                RequestOptions.create()
                        .setData(movieModel));

        // Assert
        assertEquals(201, response.status());
    }

    @Test
    public void testGetAllMoviesShouldReturnArray() {
        // Prepare
        apiRequestContext = browserContext.request();

        // Perform
        response = apiRequestContext.get(baseUrl + "/movies");

        // Assert
        assertEquals(200, response.status());
        assertTrue(ResponseHandler.arrayInResponseHasValues(response));
    }
}
