package com.movieinfo.MovieApp.repository;

import com.movieinfo.MovieApp.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Page<Movie> findAllByAccount_AccountId(Integer accountId, Pageable pageable);
}
