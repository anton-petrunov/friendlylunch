package ru.mygraduation.friendlylunch.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import ru.mygraduation.friendlylunch.HasId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static ru.mygraduation.friendlylunch.model.Restaurant.START_SEQ;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users")
public class User implements HasId {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id")
    @NotNull
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "voted_for")
    @NotNull
    private Integer votedFor;

    @Column(name = "voting_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime votingDateTime;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    public User(Integer id, String name, String email,
                String password, Date registered, Integer votedFor,
                LocalDateTime votingDateTime, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.votedFor = votedFor;
        this.votingDateTime = votingDateTime;
        setRoles(roles);
    }

    public User() {
    }

    public Integer getId() {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVotedFor(Integer votedFor) {
        this.votedFor = votedFor;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
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
                '}';
    }
}
