package com.movieinfo.MovieApp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class MovieSearchInfo {

    @SerializedName("Search")
    private List<BriefMovieInfo> search;

    private String totalResults;

    @SerializedName("Response")
    private String response;

    public List<BriefMovieInfo> getSearch() {
        return search;
    }

    public void setSearch(List<BriefMovieInfo> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieSearchInfo that = (MovieSearchInfo) o;
        return Objects.equals(search, that.search) && Objects.equals(totalResults, that.totalResults) && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(search, totalResults, response);
    }

    @Override
    public String toString() {
        return "MovieSearchInfo{" +
                "search=" + search +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
