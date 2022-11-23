package com.movieinfo.MovieApp.controller.rest;

import com.movieinfo.MovieApp.entity.Account;
import com.movieinfo.MovieApp.entity.Movie;
import com.movieinfo.MovieApp.exception.InvalidDataException;
import com.movieinfo.MovieApp.model.dto.MovieDTO;
import com.movieinfo.MovieApp.service.AccountService;
import com.movieinfo.MovieApp.service.MovieService;
import com.movieinfo.MovieApp.util.PageAndEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MovieRestController {

    private final AccountService accountService;
    private final MovieService movieService;

    @Autowired
    public MovieRestController(AccountService accountService, MovieService movieService) {
        this.accountService = accountService;
        this.movieService = movieService;
    }

    @PostMapping("/api/v1/account/{accountId}/movie/add")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Movie createMovie(@PathVariable(name = "accountId") Integer accountId,
                             @RequestBody final MovieDTO movieDTO) {
        try {
            return movieService.createMovie(accountId, movieDTO);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/{accountId}/movies")
    @PreAuthorize("hasAuthority('admins:permission')")
    public PageAndEntityResponse<Movie> getAllMovies(@PathVariable(name = "accountId") Integer accountId,
                                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                                     @RequestParam(defaultValue = "year") String sortBy) {
        try {
            return movieService.getAllMovies(accountId, pageNo - 1, pageSize, sortBy);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/api/v1/account/movie/add")
    public Movie createMovie(Authentication authentication,
                             @RequestBody final MovieDTO movieDTO) {

        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();

        try {
            Account account = accountService.getAccount(userName);
            return movieService.createMovie(account.getAccountId(), movieDTO);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/movies")
    public PageAndEntityResponse<Movie> getAllMovies(Authentication authentication,
                                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                                     @RequestParam(defaultValue = "year") String sortBy) {
        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();

        try {
            Account account = accountService.getAccount(userName);
            return movieService.getAllMovies(account.getAccountId(), pageNo - 1, pageSize, sortBy);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
