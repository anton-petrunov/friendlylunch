package ru.mygraduation.friendlylunch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.Role;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;
import ru.mygraduation.friendlylunch.web.AdminController;
import ru.mygraduation.friendlylunch.web.ProfileController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static ru.mygraduation.friendlylunch.Util.nextLunchDateTime;
import static ru.mygraduation.friendlylunch.Util.previousLunchDateTime;

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

            ProfileController profileController = appCtx.getBean(ProfileController.class);

            System.out.println(profileController.getRestaurantsWithMenuChecked());
            System.out.println(profileController.getRestaurantMenuChecked(100003));
            profileController.vote(100000, 100004);
            System.out.println(userRepository.get(100004));
            System.out.println(profileController.getUserVote(100004));
            System.out.println(profileController.getUserVote(100007));

            AdminController adminController = appCtx.getBean(AdminController.class);

            System.out.println(adminController.getRestaurant(100000));
            adminController.createRestaurant(new Restaurant(null, "CREATED MIMINO",
                    "irish bear", LocalDateTime.now()));
            System.out.println(adminController.getAllRestaurants());
            System.out.println(adminController.getRestaurant(100011));
            Restaurant restaurant = adminController.getRestaurant(100011);

            restaurant.setName("UPDATED MIMINO");
            adminController.updateRestaurant(restaurant, 100000);
            System.out.println(adminController.getRestaurant(restaurant.getId()));
            adminController.deleteRestaurant(100011);
            System.out.println(adminController.getAllRestaurants());

            adminController.createUser(new User(null, "NEW NEW",
                    "abc@def.ru", "password", new Date(), null,
                    LocalDateTime.of(2022, 1, 1, 10, 0), Set.of()));
            System.out.println(adminController.getAllUsers());

            User user = adminController.getUser(100012);
            user.setName("DOUBLE UPDATED");
            adminController.updateUser(user, 100004);

            profileController.vote(100000, 100012);

            System.out.println(adminController.getUser(100012));
            adminController.deleteUser(100008);
            System.out.println(adminController.getUser(100008));

            System.out.println(adminController.getDishes(100000));
            adminController.updateDishes("GOOD UPDATED FOOD FOR YOU", 100000);
            System.out.println(adminController.getDishes(100000));
            adminController.deleteDishes(100000);
            System.out.println(adminController.getRestaurant(100000));
        }
    }
}
