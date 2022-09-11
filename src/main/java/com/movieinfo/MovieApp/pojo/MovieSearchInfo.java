package com.movieinfo.MovieApp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
}
