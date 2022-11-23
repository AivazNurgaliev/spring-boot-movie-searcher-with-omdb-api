package com.movieinfo.MovieApp.service;

import com.movieinfo.MovieApp.exception.InvalidDataException;
import com.movieinfo.MovieApp.entity.Account;
import com.movieinfo.MovieApp.model.dto.AccountDTO;
import com.movieinfo.MovieApp.model.utility.AccountStatus;
import com.movieinfo.MovieApp.repository.AccountRepository;
import com.movieinfo.MovieApp.util.PageAndEntityResponse;
import com.movieinfo.MovieApp.model.utility.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordService passwordService;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordService passwordService) {
        this.accountRepository = accountRepository;
        this.passwordService = passwordService;
    }

    public Account getAccount(String username) throws InvalidDataException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new InvalidDataException("Accounts does not exist");
        }

        return account;
    }

    public Account createAccount(AccountDTO accountDTO) throws InvalidDataException {
        if (accountDTO == null) {
            throw new InvalidDataException("Account is null");
        }

        if (accountRepository.findByUsername(accountDTO.getUsername()) != null
            || accountRepository.findByEmail(accountDTO.getEmail()) != null) {
            throw new InvalidDataException("Account already exist or email exist");
        }
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setUsername(accountDTO.getUsername());
        String encodedPassword = passwordService.encode(accountDTO.getPassword());
        account.setPassword(encodedPassword);
        account.setRole(Role.USER);
        account.setStatus(AccountStatus.ACTIVE);
        return accountRepository.save(account);
    }

    public PageAndEntityResponse<Account> getAllAccounts(Integer pageNo,
                                                         Integer pageSize,
                                                         String sortBy) throws InvalidDataException {
        List<Account> accounts = accountRepository.findAll();

        if (accounts == null || accounts.size() == 0) {
            throw new InvalidDataException("Accounts does not exist");
        }

        Pageable paging;
        if (sortBy.startsWith("-")) {
            StringBuilder sb = new StringBuilder(sortBy);
            sortBy = sb.deleteCharAt(0).toString();
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }

        Page<Account> pagedResult = accountRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            PageAndEntityResponse<Account> accountPage = new PageAndEntityResponse<>();
            accountPage.setTotalPages(pagedResult.getTotalPages());
            accountPage.setTotalElements(pagedResult.getTotalElements());
            accountPage.setEntity(pagedResult.getContent());

            return accountPage;
        } else {
            throw new InvalidDataException("There's no accounts in database or " +
                    "Invalid paging or sorting params or content does not exist");
        }
    }

    public Account deleteAccount(Integer accountId) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new InvalidDataException("Account does not exist, wrong account id");
        }

        accountRepository.delete(account);
        return account;
    }

    @Transactional
    public Account updateEmail(Integer accountId, String newEmail) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (accountRepository.findByEmail(newEmail) != null) {
            throw new InvalidDataException("Account already exist with this email");
        }
        account.setEmail(newEmail);
        return account;
    }

    @Transactional
    public Account updateUsername(Integer accountId, String newUsername) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (accountRepository.findByUsername(newUsername) != null) {
            throw new InvalidDataException("Account already exist with this username");
        }
        account.setUsername(newUsername);
        return account;
    }

    @Transactional
    public Account updatePassword(Integer accountId, String oldPass, String newPass) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (passwordService.validatePassword(passwordService.encode(oldPass), account.getPassword())) {
            throw new InvalidDataException("Wrong password");
        }


        account.setPassword(passwordService.encode(newPass));
        return account;
    }

    @Transactional
    public Account updateRole(Integer accountId, String newRole) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        newRole = newRole.toUpperCase();

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (!newRole.equals("USER") && !newRole.equals("ADMIN")) {
            throw new InvalidDataException("Invalid role");
        }

        if (newRole.equals("USER")) {
            account.setRole(Role.USER);
        } else if (newRole.equals("ADMIN")){
            account.setRole(Role.ADMIN);
        }
        return account;
    }

    @Transactional
    public Account updateStatus(Integer accountId, String newStatus) throws InvalidDataException {
        Account account = accountRepository.findByAccountId(accountId);

        newStatus = newStatus.toUpperCase();

        if (account == null) {
            throw new InvalidDataException("Account does not exist");
        }

        if (!newStatus.equals("ACTIVE") && !newStatus.equals("BANNED")) {
            throw new InvalidDataException("Invalid status");
        }

        if (newStatus.equals("ACTIVE")) {
            account.setStatus(AccountStatus.ACTIVE);
        } else if (newStatus.equals("BANNED")){
            account.setStatus(AccountStatus.BANNED);
        }
        return account;
    }

}
