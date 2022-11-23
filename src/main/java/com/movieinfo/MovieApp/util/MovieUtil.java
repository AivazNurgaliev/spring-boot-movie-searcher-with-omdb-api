package com.movieinfo.MovieApp.util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.movieinfo.MovieApp.exception.MovieNotFoundException;
import com.movieinfo.MovieApp.model.MovieInfo;
import com.movieinfo.MovieApp.model.MovieSearchInfo;
import org.springframework.web.client.RestTemplate;

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
        MovieInfo movieInfo = gson.fromJson(serializedObj, MovieInfo.class);
        if (movieInfo.getTitle() == null) {
            throw new MovieNotFoundException("Incorrect Id");
        }
        return serializedObj;
    }


    public static String makeRequestByName(String name,
                                           String type,
                                           String y) throws MovieNotFoundException {
        String uri = "http://www.omdbapi.com/?s=" + name + "&apikey=" + apikey +
                "&type=" + type + "&y=" + y + "";
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        Object obj = restTemplate.getForObject(uri, Object.class);
        if (obj == null) {
            throw new JsonParseException("Incorrect request");
        }
        String serializedObj = gson.toJson(obj);
        MovieSearchInfo movieSearchInfo = gson.fromJson(serializedObj, MovieSearchInfo.class);
        if (movieSearchInfo.getSearch() == null) {
            throw new MovieNotFoundException("Incorrect name, type or year");
        }
        return serializedObj;
    }
}
