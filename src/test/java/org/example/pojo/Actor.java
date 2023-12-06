package org.example.pojo;

public class Actor {

    private long id;
    private String firstName;
    private String lastName;
    private String birthCountry;
    private long movieId;

    public Actor(long id, String firstName, String lastName, String birthCountry, long movieId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthCountry = birthCountry;
        this.movieId = movieId;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }
}
