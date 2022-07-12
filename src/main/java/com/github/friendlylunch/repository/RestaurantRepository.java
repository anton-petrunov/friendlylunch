package com.github.friendlylunch.repository;

import com.github.friendlylunch.model.Restaurant;
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
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r ORDER BY r.name ASC")
    List<Restaurant> getAll();

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithMenus(int id);

    @Query("SELECT r FROM Restaurant r JOIN r.menus m JOIN m.dishes d " +
            "WHERE m.date = ?1 AND size(m.dishes) > 0 GROUP BY r")
    List<Restaurant> getAllCheckedByMenuDateAndDishesSize(LocalDate date);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select r from Restaurant r join r.menus m join m.dishes d " +
            "where m.date = ?1 and size(m.dishes) > 0 group by m")
    List<Restaurant> getAllCheckedByMenuDateAndDishesSizeWithMenus(LocalDate date);

    @Query("SELECT r FROM Restaurant r JOIN r.menus m " +
            "WHERE r.id = ?1 AND m.date = ?2 AND size(m.dishes) > 0")
    Restaurant getCheckedByMenuDateAndDishesSize(int id, LocalDate date);
}
