package org.example.model;

public class MovieModel {
    private Long id;
    private String title;
    private String writer;
    private String director;
    private String genre;
    private String awards;
    private int releaseYear;

    public MovieModel(Long id, String title, String writer, String director, String genre, String awards, int releaseYear) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.director = director;
        this.genre = genre;
        this.awards = awards;
        this.releaseYear = releaseYear;
    }

    public MovieModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
