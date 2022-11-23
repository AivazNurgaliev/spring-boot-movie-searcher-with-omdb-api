package com.movieinfo.MovieApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "imdbID")
    private String imdbID;

    @Column(name = "type")
    private String type;

    @Column(name = "poster")
    private String poster;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonIgnore
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(movieId, movie.movieId) && Objects.equals(title, movie.title) && Objects.equals(year, movie.year) && Objects.equals(imdbID, movie.imdbID) && Objects.equals(type, movie.type) && Objects.equals(poster, movie.poster) && Objects.equals(account, movie.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, title, year, imdbID, type, poster, account);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                ", account=" + account +
                '}';
    }
}
