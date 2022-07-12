package com.github.friendlylunch.repository;

import com.github.friendlylunch.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.menu.id = ?1 AND d.id = ?2")
    int delete(int menuId, int id);

    @Query("SELECT d FROM Dish d WHERE d.menu.restaurant.id = ?1 AND d.menu.id = ?2")
    List<Dish> getAll(int restaurantId, int menuId);

    @Query("SELECT d FROM Dish d WHERE d.menu.restaurant.id = ?1 AND d.menu.id = ?2 AND d.id = ?3")
    Dish get(int restaurantId, int menuId, int id);

    @Query("SELECT d FROM Dish d JOIN FETCH d.menu m " +
            "WHERE m.restaurant.id = ?1 AND m.id = ?2 AND m.date = ?3 AND size(m.dishes) > 0")
    List<Dish> getAllCheckedByMenuDateAndDishesSize(int restaurantId, int menuId, LocalDate date);

    @Query("select d FROM Dish d where d.menu.restaurant.id = ?1 " +
            "and d.menu.id = ?2 and d.id = ?3 and d.menu.date = ?4 and size(d.menu.dishes) > 0 ")
    Dish getCheckedByMenuDateAndDishesSize(int restaurantId, int menuId, int id, LocalDate date);
}
