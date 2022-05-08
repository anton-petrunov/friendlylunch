package ru.mygraduation.friendlylunch.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r ORDER BY r.dishesUpdateDateTime DESC"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET_BETWEEN, query = """
                    SELECT r FROM Restaurant r
                    WHERE r.dishesUpdateDateTime>:startDateTime AND r.dishesUpdateDateTime<:endDateTime
                """),
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"id", "name"}, name = "restaurants_unique_id_name_idx")})
public class Restaurant {
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET_BETWEEN = "Restaurant.getBetween";

    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id")
    @NotNull
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String name;

    @Column(name = "dishes")
    @Size(min = 2, max = 1200)
    private String dishes;

    @Column(name = "updating_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dishesUpdateDateTime;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String dishes, LocalDateTime dishesUpdateDateTime) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
        this.dishesUpdateDateTime = dishesUpdateDateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDishes() {
        return dishes;
    }

    public LocalDateTime getDishesUpdateDateTime() {
        return dishesUpdateDateTime;
    }

    public boolean isNew() {
        return getId() == null;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDishesUpdateDateTime(LocalDateTime dishesUpdateDateTime) {
        this.dishesUpdateDateTime = dishesUpdateDateTime;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes='" + dishes + '\'' +
                ", votingDateTime=" + dishesUpdateDateTime +
                '}';
    }
}
