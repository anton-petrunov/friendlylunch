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

    @Query("SELECT d FROM Dish d WHERE d.menu.id = ?1")
    List<Dish> getAll(int menuId);

    @Query("SELECT d FROM Dish d WHERE d.menu.id = ?1 AND d.id = ?2")
    Dish get(int menuId, int id);

    @Query("SELECT d FROM Dish d WHERE d.menu.id = ?1 AND d.menu.date = ?2 AND size(d.menu.dishes) > 0")
    List<Dish> getAllCheckedByMenuDateAndDishesSize(int menuId, LocalDate date);

    @Query("select d FROM Dish d where d.menu.id = ?1 " +
            "and d.id = ?2 and d.menu.date = ?3 and size(d.menu.dishes) > 0 ")
    Dish getCheckedByMenuDateAndDishesSize(int menuId, int id, LocalDate date);
}
