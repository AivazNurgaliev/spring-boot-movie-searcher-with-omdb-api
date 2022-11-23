package com.movieinfo.MovieApp.repository;

import com.movieinfo.MovieApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    Account findByAccountId(Integer accountId);
    Account findByEmail(String email);

}
