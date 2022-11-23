package com.movieinfo.MovieApp.controller;

import com.movieinfo.MovieApp.entity.Account;
import com.movieinfo.MovieApp.exception.InvalidDataException;
import com.movieinfo.MovieApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class MainController {

    private final AccountService accountService;

    @Autowired
    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/")
    public String overview() {
        return "overview";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup/success")
    public String signupSuccess() {
        //System.out.println("4");
        return "success";
    }

    @GetMapping ("/api/v1/account/delete")
    public String deleteAccount(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();
        try {
            Account account = accountService.getAccount(userName);
            accountService.deleteAccount(account.getAccountId());
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return "redirect:/signup";
    }

/*    @GetMapping("/signup/e")
    public void signupw() {
        System.out.println("damn");;
    }*/

}
