package com.movieinfo.MovieApp.util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.movieinfo.MovieApp.exception.MovieNotFoundException;
import com.movieinfo.MovieApp.pojo.BriefMovieInfo;
import com.movieinfo.MovieApp.pojo.MovieInfo;
import com.movieinfo.MovieApp.pojo.MovieSearchInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieUtil {

    private static final String apikey = "3fe95daa";

    public static String makeRequestById(String id) throws MovieNotFoundException, JsonParseException {
        String uri = "http://www.omdbapi.com/?i=" + id + "&plot=full&apikey=" + apikey + "";
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        Object obj = restTemplate.getForObject(uri, Object.class);
        if (obj == null) {
            throw new JsonParseException("Incorrect request");
        }
        String serializedObj = gson.toJson(obj);
        //deserialized from serializedObj String
        MovieInfo movieInfo = gson.fromJson(serializedObj, MovieInfo.class);
        if (movieInfo.getTitle() == null) {
            throw new MovieNotFoundException("Incorrect Id");
        }
        return serializedObj;
    }

/*    public static MovieSearchInfo makeRequestByName(String name) throws MovieNotFoundException {
        String uri = "http://www.omdbapi.com/?s=" + name + "&apikey=" + apikey + "";
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        Object obj = restTemplate.getForObject(uri, Object.class);
        if (obj == null) {
            throw new JsonParseException("Incorrect request");
        }
        String json = gson.toJson(obj);
        MovieSearchInfo movieSearchInfo = gson.fromJson(json, MovieSearchInfo.class);
        if (movieSearchInfo.getSearch() == null) {
            throw new MovieNotFoundException("Incorrect name");
        }
        return movieSearchInfo;
    }*/

    public static String makeRequestByName(String name) throws MovieNotFoundException {
        String uri = "http://www.omdbapi.com/?s=" + name + "&apikey=" + apikey + "";
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        Object obj = restTemplate.getForObject(uri, Object.class);
        if (obj == null) {
            throw new JsonParseException("Incorrect request");
        }
        String serializedObj = gson.toJson(obj);
        MovieSearchInfo movieSearchInfo = gson.fromJson(serializedObj, MovieSearchInfo.class);
        if (movieSearchInfo.getSearch() == null) {
            throw new MovieNotFoundException("Incorrect name");
        }
        return serializedObj;
    }
}
