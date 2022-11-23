package com.movieinfo.MovieApp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class BriefMovieInfo {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    private String imdbID;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

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
        BriefMovieInfo that = (BriefMovieInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(year, that.year) && Objects.equals(imdbID, that.imdbID) && Objects.equals(type, that.type) && Objects.equals(poster, that.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, imdbID, type, poster);
    }

    @Override
    public String toString() {
        return "BriefMovieInfo{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }



}
