package org.example.test;

import com.microsoft.playwright.*;
import org.example.RESTservice.RequestHandler;
import org.example.RESTservice.ResponseHandler;
import org.example.model.MovieModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieApiTest {

    private static String baseUrl;
    private static APIResponse response;
    private static RequestHandler requestHandler;
    private static MovieModel movieModel;

    @BeforeAll
    static void setup() {
        requestHandler = new RequestHandler();
        baseUrl = "http:/localhost:8080/api/v1";
    }

    @Test
    public void testGetMoviebByIdShouldPass() {
        // Prepare
        long movieId = 6L;

        // Perform
        response = requestHandler.getRequest(baseUrl + "/movie/id/" + movieId);

        // Assert
        assertEquals(200, response.status());
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
        movieModel = new MovieModel(100L,
                "The Tale of The Most Quintessential Tales",
                "Guy Rithcie",
                "Guy Rithcie",
                "Action/Comedy",
                "Very good awards",
                2023);

        // Perform
        response = requestHandler.postRequest(baseUrl + "/movie/add", movieModel);

        // Assert
        assertEquals(201, response.status());
        assertTrue(response.text().contains("Created"));
    }

    @Test
    public void testGetAllMoviesShouldReturnArray() {
        // Perform
        response = requestHandler.getRequest(baseUrl + "/movies");

        // Assert
        assertEquals(200, response.status());
        assertTrue(ResponseHandler.arrayInResponseHasValues(response));
    }
}
