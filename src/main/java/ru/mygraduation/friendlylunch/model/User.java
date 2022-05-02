package ru.mygraduation.friendlylunch.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date registered;
    private int votedFor;
    private LocalDateTime votingDateTime;
    private Set roles;

    public User(int id, String name, String email, String password, Date registered, int votedFor, LocalDateTime votingDateTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.votedFor = votedFor;
        this.votingDateTime = votingDateTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegistered() {
        return registered;
    }

    public int getVotedFor() {
        return votedFor;
    }

    public LocalDateTime getVotingDateTime() {
        return votingDateTime;
    }

    public Set getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setVotedFor(int votedFor) {
        this.votedFor = votedFor;
    }

    public void setVotingDateTime(LocalDateTime votingDateTime) {
        this.votingDateTime = votingDateTime;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", votedFor=" + votedFor +
                ", votingDateTime=" + votingDateTime +
                ", roles=" + roles +
                '}';
    }
}
