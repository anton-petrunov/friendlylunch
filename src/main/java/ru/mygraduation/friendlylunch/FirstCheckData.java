package ru.mygraduation.friendlylunch;

import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FirstCheckData {
    public static User user1 = new User(1, "Anton", "a.n.tone@mail.ru", "psswd",
            new Date(), 4, LocalDateTime.of(2022, Month.APRIL, 28, 22, 36));

    public static User user2 = new User(2, "Kirill", "gilly@amail.ru", "password", new Date(),
            3, LocalDateTime.now());

    public static User user3 = new User(3, "Annet", "gmail@gmail.com", "pass", new Date(),
            2, LocalDateTime.of(2022, Month.APRIL, 29, 11, 0));

    public static User user4 = new User(4, "Pirouette", "mao@mao.ru", "smoke", new Date(),
            1, LocalDateTime.of(2022, Month.MAY, 1, 15, 0));

    public static User user5 = new User(5, "Man whithout vote", "man@without.vote", "", new Date(),
            0, null);

    public static Restaurant restaurant1 = new Restaurant(1, "Shaurma", "shaurma=180;coffee=60.00",
            LocalDateTime.now());

    public static Restaurant restaurant2 = new Restaurant(2, "Fresh Apple Lunches",
            "apple=23.0;orange=49;mango=169.45",
            LocalDateTime.of(2022, Month.APRIL, 28, 18, 30));

    public static Restaurant restaurant3 = new Restaurant(3, "Papa Grill", "Chicken grill=299.90;Cow grill=349.90",
            LocalDateTime.of(2022, Month.MAY, 1, 14, 58));

    public static Restaurant restaurant4 = new Restaurant(4, "Funny Fish", "Fish stake=310.9;Sushi=500.20",
            LocalDateTime.now());

    public static List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2, restaurant3, restaurant4);
    public static List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

}
