package com.movieinfo.MovieApp.service;

import com.movieinfo.MovieApp.entity.Account;
import com.movieinfo.MovieApp.entity.Movie;
import com.movieinfo.MovieApp.exception.InvalidDataException;
import com.movieinfo.MovieApp.model.dto.MovieDTO;
import com.movieinfo.MovieApp.repository.AccountRepository;
import com.movieinfo.MovieApp.repository.MovieRepository;
import com.movieinfo.MovieApp.util.PageAndEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MovieService {

    private final AccountRepository accountRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(AccountRepository accountRepository,
                        MovieRepository movieRepository) {
        this.accountRepository = accountRepository;
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Integer accountId, MovieDTO movieDTO) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (movieDTO == null) {
            throw new InvalidDataException("Movie is null");
        }

        Movie movie = movieDTO.toMovie(account);
        return movieRepository.save(movie);
    }

    public PageAndEntityResponse<Movie> getAllMovies(Integer accountId,
                                                     Integer pageNo,
                                                     Integer pageSize,
                                                     String sortBy) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (account.getMovieList().size() == 0 || account.getMovieList() == null) {
            throw new InvalidDataException("Movie list does not exist");
        }

        Pageable paging;
        if (sortBy.startsWith("-")) {
            StringBuilder sb = new StringBuilder(sortBy);
            sortBy = sb.deleteCharAt(0).toString();
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }

        Page<Movie> pagedResult = movieRepository.findAllByAccount_AccountId(accountId, paging);
        if (pagedResult.hasContent()) {
            PageAndEntityResponse<Movie> moviePage = new PageAndEntityResponse<>();
            moviePage.setTotalPages(pagedResult.getTotalPages());
            moviePage.setTotalElements(pagedResult.getTotalElements());
            moviePage.setEntity(pagedResult.getContent());

            return moviePage;
        } else {
            throw new InvalidDataException("There's no movie in database or " +
                    "Invalid paging or sorting params or content does not exist");
        }
    }

}
