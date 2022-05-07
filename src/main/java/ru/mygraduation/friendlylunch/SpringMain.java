package ru.mygraduation.friendlylunch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;
import ru.mygraduation.friendlylunch.web.ProfileControllerPrototype;

import java.time.LocalDateTime;
import java.util.Date;

import static ru.mygraduation.friendlylunch.FirstCheckData.*;
import static ru.mygraduation.friendlylunch.Util.*;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
            ProfileControllerPrototype profileControllerPrototype = appCtx.getBean(ProfileControllerPrototype.class);

            System.out.println("\n============ SANDBOX SPRING_MAIN DATA ==========");
            System.out.println("Время обеда: " + LUNCH_TIME);
            System.out.println("Дата и время сейчас: " + dateTimeNow);
            System.out.println("Дата и время следующего обеда: " + nextLunchDateTime());
            System.out.println("Дата и время последнего прошедшего обеда: " + previousLunchDateTime());
            System.out.println("Время окончания голосования: " + STOP_VOTING_TIME);
            System.out.println("Дата и время ближайшего окончания голосования: " + stopVotingDateTime());
            System.out.println("Данные ресторана №3: " + restaurant3.toString());
            System.out.println("Данные юзера №1: " + user1.toString());

            System.out.println("\nAll users and all restaurants data: ");
            System.out.println("\n" + users.toString() + "\n" + restaurants.toString() + "\n");
            System.out.println("Menu of restaurant 1 is actual? " + checkMenu(restaurant1));
            System.out.println("Menu of restaurant 2 is actual? " + checkMenu(restaurant2));
            System.out.printf("Menu of restaurant 3 is actual? - %s\n", checkMenu(restaurant3));
            System.out.println("Menu of restaurant 4 is actual? " + checkMenu(restaurant4));
            System.out.println("Check vote of user 3: " + checkVote(user3));
            System.out.println("Check revote availability for user 4 " + checkRevoteAvailability(user4));
            System.out.println("==============");

            System.out.println("Print ALL restaurants with menu checked: \n" + profileControllerPrototype.getAllRestaurantsForVoting());
            System.out.println("Print menu of restaurant 3");
            System.out.println(profileControllerPrototype.getRestaurantMenuForVoting(3) + "\n");

//       TODO   If I try to getRestaurantMenuForVoting with id not exist I receive NPE

            System.out.println("User 5 WAS: " + user5);
            profileControllerPrototype.vote(4, 5);
            System.out.println("User 5 votes for restaurant 4");
            System.out.println("NOW user 5 is: " + user5);

            profileControllerPrototype.vote(4, 4);
            System.out.println("User 4 votes for restaurant 4");
            System.out.println("\nCheck voting status of User 4\n");
            System.out.println(profileControllerPrototype.checkVotingStatus(4));

            System.out.println("\nCheck voting results: Restaurant ID = Count of votes");
            System.out.println(profileControllerPrototype.checkVotingResults());

            System.out.println("\nUser 2 WAS: " + user2);
            System.out.println("Check vote of user 2 is valid: " + checkVote(user2));
            System.out.println("Check voting availability for user 2: " + checkVotingAvailability(user2));
            System.out.println("Check revote availability for user 2: " + checkRevoteAvailability(user2));

            profileControllerPrototype.vote(1, 2);
            System.out.println("User 2 votes for restaurant 1");
            System.out.println("User 2 IS: " + user2);

            System.out.println("\nUser 3 WAS: " + user3);
            System.out.println("Check vote of user 3 is valid: " + checkVote(user3));
            System.out.println("Check voting availability for user 3: " + checkVotingAvailability(user3));
            System.out.println("Check revote availability for user 3: " + checkRevoteAvailability(user3));
            profileControllerPrototype.vote(1, 3);
            System.out.println("User 3 votes for restaurant 1");
            System.out.println("User3 IS: " + user3);

            System.out.println("\nCheck voting results: Restaurant ID = Count of votes");
            System.out.println(profileControllerPrototype.checkVotingResults());
            profileControllerPrototype.vote(4, 1);
            System.out.println("\nUser 1 votes for restaurant 4");

            System.out.println("Check voting results: Restaurant ID = Count of votes");
            System.out.println(profileControllerPrototype.checkVotingResults());

            RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepository.class);
            System.out.println("\nGet restaurants with menu updated between previous and next Lunch\n");
            System.out.println(restaurantRepository.getBetween(previousLunchDateTime(), nextLunchDateTime()));
            System.out.println("\nGet restaurant with id 100001\n");
            System.out.println(restaurantRepository.get(100001));
            System.out.println("\nDelete restaurant with id 100000\n");
            restaurantRepository.delete(100000);
            System.out.println(restaurantRepository.getAll());
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
                    "p", new Date(),null, null));
            userRepository.save(new User(100008, "UPDATED", "post",
                    "psswd", new Date(), null, null));
            System.out.println(userRepository.getAll());
            System.out.println("\nDelete user 100006 'Annet'\n");
            userRepository.delete(100006);
            System.out.println(userRepository.getAll());
        }
    }
}
