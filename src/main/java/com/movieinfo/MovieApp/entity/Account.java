package com.movieinfo.MovieApp.entity;

import com.movieinfo.MovieApp.model.utility.AccountStatus;
import com.movieinfo.MovieApp.model.utility.Role;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "username")
    @NotNull
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    @NotNull
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private AccountStatus status;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Movie> movieList;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer account_id) {
        this.accountId = account_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) && Objects.equals(email, account.email) && Objects.equals(password, account.password) && Objects.equals(username, account.username) && role == account.role && status == account.status && Objects.equals(movieList, account.movieList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, email, password, username, role, status, movieList);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", movieList=" + movieList +
                '}';
    }
}
