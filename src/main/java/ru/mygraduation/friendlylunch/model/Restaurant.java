package ru.mygraduation.friendlylunch.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "dishes")
    @Size(min = 2, max = 1200)
    private String dishes;

    @Column(name = "updating_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dishesUpdateDateTime;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String dishes, LocalDateTime dishesUpdateDateTime) {
        super(id, name);
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
