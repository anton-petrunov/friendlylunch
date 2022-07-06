package com.github.friendlylunch.repository;

import com.github.friendlylunch.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.id=:id")
    int delete(@Param("restaurantId") int restaurantId, @Param("id") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.id=:id")
    Menu get(@Param("restaurantId") int restaurantId, @Param("id") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    List<Menu> getAllByDateWithDishes(@Param("date") LocalDate date);
}
