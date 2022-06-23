package com.github.friendlylunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.github.friendlylunch.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r ORDER BY r.dishesUpdateDateTime DESC")
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.dishesUpdateDateTime>:startDateTime AND r.dishesUpdateDateTime<:endDateTime")
    List<Restaurant> getBetween(@Param("startDateTime") LocalDateTime startDateTime,
                                @Param("endDateTime") LocalDateTime endDateTime);
}
