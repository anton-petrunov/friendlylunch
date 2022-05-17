package ru.mygraduation.friendlylunch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.Role;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;
import ru.mygraduation.friendlylunch.web.admin.AbstractAdminController;
import ru.mygraduation.friendlylunch.web.profile.AbstractProfileController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static ru.mygraduation.friendlylunch.util.Util.nextLunchDateTime;
import static ru.mygraduation.friendlylunch.util.Util.previousLunchDateTime;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext(
                "spring/spring-app.xml",
                "spring/spring-db.xml",
                "spring/spring-mvc.xml")) {

            System.out.println("\n============ SANDBOX SPRING_MAIN DATA ==========");

            RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepository.class);

            System.out.println("\nGet restaurants with menu updated between previous and next Lunch\n");
            System.out.println(restaurantRepository.getBetween(previousLunchDateTime(), nextLunchDateTime()));
            System.out.println("\nGet restaurant with id 100001\n");
            System.out.println(restaurantRepository.get(100001));
//
            System.out.println("\nSave new restaurant 'Created'\n");
            restaurantRepository.save(new Restaurant(
                    null, "CREATED restaurant", "dises of new restaraunt", LocalDateTime.now()));
            System.out.println(restaurantRepository.getAll());
            System.out.println("\nSave restaurant with id 100003 to 'Updated'\n");
            restaurantRepository.save(new Restaurant(
                    100003, "UPDATED", "updated dishes", LocalDateTime.now()));
            System.out.println(restaurantRepository.getAll());

            UserRepository userRepository = appCtx.getBean(UserRepository.class);

            System.out.println("\nGet all users from Repository\n");
            System.out.println(userRepository.getAll());
            System.out.println("\nGet user with id 100008\n");
            System.out.println(userRepository.get(100008));
            System.out.println("\nGet ME by email\n");
            System.out.println(userRepository.getByEmail("a.n.tone@mail.ru"));
            System.out.println("\nSave NEW user 'Created' and save user 100008 to 'Updated'\n");
            userRepository.save(new User(
                    null, "Katherine", "cat@cat.cat",
                    "p", new Date(), null, null, Set.of()));
            userRepository.save(new User(100008, "UPDATED", "post",
                    "psswd", new Date(), null, null, Set.of(Role.ADMIN)));
            System.out.println(userRepository.getAll());
            System.out.println("\nDelete user 100006 'Annet'\n");
            userRepository.delete(100006);
            System.out.println(userRepository.getAll());

            AbstractProfileController AbstractProfileController = appCtx.getBean(AbstractProfileController.class);

            System.out.println(AbstractProfileController.getRestaurantsWithMenuChecked());
            System.out.println(AbstractProfileController.getRestaurantMenuChecked(100003));
            AbstractProfileController.vote(100000, 100004);
            System.out.println(userRepository.get(100004));
            System.out.println(AbstractProfileController.getUserVote(100004));
            System.out.println(AbstractProfileController.getUserVote(100007));

            AbstractAdminController abstractAdminController = appCtx.getBean(AbstractAdminController.class);

            System.out.println(abstractAdminController.getRestaurant(100000));
            abstractAdminController.createRestaurant(new Restaurant(null, "CREATED MIMINO",
                    "irish bear", LocalDateTime.now()));
            System.out.println(abstractAdminController.getAllRestaurants());
            System.out.println(abstractAdminController.getRestaurant(100011));
            Restaurant restaurant = abstractAdminController.getRestaurant(100011);

            restaurant.setName("UPDATED MIMINO");
            abstractAdminController.updateRestaurant(restaurant, 100000);
            System.out.println(abstractAdminController.getRestaurant(restaurant.getId()));
            abstractAdminController.deleteRestaurant(100011);
            System.out.println(abstractAdminController.getAllRestaurants());

            abstractAdminController.createUser(new User(null, "NEW NEW",
                    "abc@def.ru", "password", new Date(), null,
                    LocalDateTime.of(2022, 1, 1, 10, 0), Set.of()));
            System.out.println(abstractAdminController.getAllUsers());

            User user = abstractAdminController.getUser(100012);
            user.setName("DOUBLE UPDATED");
            abstractAdminController.updateUser(user, 100004);

            AbstractProfileController.vote(100000, 100012);

            System.out.println(abstractAdminController.getUser(100012));
            abstractAdminController.deleteUser(100008);
            System.out.println(abstractAdminController.getUser(100008));

            System.out.println(abstractAdminController.getDishes(100000));
            abstractAdminController.updateDishes("GOOD UPDATED FOOD FOR YOU", 100000);
            System.out.println(abstractAdminController.getDishes(100000));
            abstractAdminController.deleteDishes(100000);
            System.out.println(abstractAdminController.getRestaurant(100000));
        }
    }
}
