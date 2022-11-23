package com.movieinfo.MovieApp.model.dto;

import com.movieinfo.MovieApp.entity.Account;
import com.movieinfo.MovieApp.entity.Movie;

import java.util.Objects;

public class MovieDTO {

    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;

    public Movie toMovie(Account account) {
        Movie movie = new Movie();

        movie.setAccount(account);
        movie.setTitle(title);
        movie.setYear(year);
        movie.setImdbID(imdbID);
        movie.setType(type);
        movie.setPoster(poster);

        return movie;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(title, movieDTO.title) && Objects.equals(year, movieDTO.year) && Objects.equals(imdbID, movieDTO.imdbID) && Objects.equals(type, movieDTO.type) && Objects.equals(poster, movieDTO.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, imdbID, type, poster);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
