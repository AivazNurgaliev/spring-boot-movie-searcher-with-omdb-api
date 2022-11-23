package com.movieinfo.MovieApp.controller.rest;

import com.movieinfo.MovieApp.exception.InvalidDataException;
import com.movieinfo.MovieApp.entity.Account;
import com.movieinfo.MovieApp.model.dto.AccountDTO;
import com.movieinfo.MovieApp.service.AccountService;
import com.movieinfo.MovieApp.util.PageAndEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountRestController {

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public String createAccount(@RequestBody final AccountDTO accountDTO) {
        try {
            accountService.createAccount(accountDTO);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return "redirect:/login";
    }

    @GetMapping("/api/v1/accounts")
    @PreAuthorize("hasAuthority('admins:permission')")
    public PageAndEntityResponse<Account> getAllAccounts(@RequestParam(defaultValue = "1") Integer pageNo,
                                                         @RequestParam(defaultValue = "5") Integer pageSize,
                                                         @RequestParam(defaultValue = "accountId") String sortBy) {
        try {
            return accountService.getAllAccounts(pageNo - 1, pageSize, sortBy);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping ("/api/v1/account/{accountId}/delete")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Account deleteAccountById(@PathVariable(name = "accountId") Integer accountId) {
        try {
            return accountService.deleteAccount(accountId);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/{accountId}/change/email/{newEmail}")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Account editEmail(@PathVariable(name = "accountId") Integer accountId,
                             @PathVariable(name = "newEmail") String newEmail) {

        try {
            return accountService.updateEmail(accountId, newEmail);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/{accountId}/change/username/{newUsername}")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Account editUsername(@PathVariable(name = "accountId") Integer accountId,
                                @PathVariable(name = "newUsername") String newUsername) {

        try {
            return accountService.updateUsername(accountId, newUsername);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/{accountId}/change/password/{oldPass}/{newPass}")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Account editPassword(@PathVariable(name = "accountId") Integer accountId,
                                @PathVariable(name = "oldPass") String oldPass,
                                @PathVariable(name = "newPass") String newPass) {

        try {
            return accountService.updatePassword(accountId, oldPass, newPass);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/{accountId}/change/role/{newRole}")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Account editRole(@PathVariable(name = "accountId") Integer accountId,
                            @PathVariable(name = "newRole") String newRole) {

        try {
            return accountService.updateRole(accountId, newRole);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/{accountId}/change/status/{newStatus}")
    @PreAuthorize("hasAuthority('admins:permission')")
    public Account editStatus(@PathVariable(name = "accountId") Integer accountId,
                            @PathVariable(name = "newStatus") String newStatus) {

        try {
            return accountService.updateStatus(accountId, newStatus);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/get")
    public Account getAccount(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();

        try {
            return accountService.getAccount(userName);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("/api/v1/account/change/email/{newEmail}")
    public Account editEmail(Authentication authentication,
                             @PathVariable(name = "newEmail") String newEmail) {

        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();

        try {
            Account account = accountService.getAccount(userName);
            return accountService.updateEmail(account.getAccountId(), newEmail);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/change/username/{newUsername}")
    public Account editUsername(Authentication authentication,
                                @PathVariable(name = "newUsername") String newUsername) {


        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();

        try {
            Account account = accountService.getAccount(userName);
            return accountService.updateUsername(account.getAccountId(), newUsername);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/api/v1/account/change/password/{oldPass}/{newPass}")
    public Account editPassword(Authentication authentication,
                                @PathVariable(name = "oldPass") String oldPass,
                                @PathVariable(name = "newPass") String newPass) {

        if (authentication == null) {
            return null;
        }

        String userName = authentication.getName();

        try {
            Account account = accountService.getAccount(userName);
            return accountService.updatePassword(account.getAccountId(), oldPass, newPass);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
