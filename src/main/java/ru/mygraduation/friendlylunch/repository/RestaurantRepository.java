package ru.mygraduation.friendlylunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mygraduation.friendlylunch.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RestaurantRepository {

    private final CrudRestaurantRepository crudRepository;

    public RestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return crudRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return crudRepository.getAll();
    }

    public List<Restaurant> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return crudRepository.getBetween(startDateTime, endDateTime);
    }
}