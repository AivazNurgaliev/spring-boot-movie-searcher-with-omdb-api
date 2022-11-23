package com.movieinfo.MovieApp.controller.rest;

import com.google.gson.JsonParseException;
import com.movieinfo.MovieApp.exception.MovieNotFoundException;
import com.movieinfo.MovieApp.util.MovieUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MovieSearchRestController {

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
    public String getMoviesByName(@RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String y) {
        try {
            String movieSearchInfo = MovieUtil.makeRequestByName(name, type, y);
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
