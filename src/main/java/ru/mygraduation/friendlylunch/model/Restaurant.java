package ru.mygraduation.friendlylunch.model;

import java.time.LocalDateTime;

public class Restaurant {
    private int id;
    private String name;
    private String dishes;
    private LocalDateTime dishesUpdateDateTime;

    public Restaurant(int id, String name, String dishes, LocalDateTime dishesUpdateDateTime) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
        this.dishesUpdateDateTime = dishesUpdateDateTime;
    }

    public int getId() {
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
