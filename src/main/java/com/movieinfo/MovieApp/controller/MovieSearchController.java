package com.movieinfo.MovieApp.controller;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.movieinfo.MovieApp.exception.MovieNotFoundException;
import com.movieinfo.MovieApp.pojo.BriefMovieInfo;
import com.movieinfo.MovieApp.pojo.MovieInfo;
import com.movieinfo.MovieApp.pojo.MovieSearchInfo;
import com.movieinfo.MovieApp.util.MovieUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MovieSearchController {

    @GetMapping("/movie/byId")
    public String getMovieById(@RequestParam String id) {
        try {
            String movieInfo = MovieUtil.makeRequestById(id);
            return movieInfo;
        } catch (JsonParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/movie/byName")
    public String getMoviesByName(@RequestParam String name) {
        try {
            String movieSearchInfo = MovieUtil.makeRequestByName(name);
            return movieSearchInfo;
        } catch (JsonParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (MovieNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
