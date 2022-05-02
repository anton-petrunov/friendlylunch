package ru.mygraduation.friendlylunch;

import static ru.mygraduation.friendlylunch.FirstCheckData.*;
import static ru.mygraduation.friendlylunch.Util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n============ SANDBOX MAIN DATA ==========");
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

        ProfileControllerPrototype profileControllerPrototype = new ProfileControllerPrototype();

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
    }
}
