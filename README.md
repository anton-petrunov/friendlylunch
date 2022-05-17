Hello!

This "**FriendlyLunch**" REST API was born as a result of completing the final task of the [**TopJava internship**](https://topjava.ru/topjava).

I was literally told that "a graduation project is a fully independent work," and so it happened. You can see a working timing in the commits.

The full version of the assignment is [**here**](https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md). And here is the original text of task as is:

>Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
The task is:
> 
>Build a voting system for deciding where to have lunch.
>* 2 types of users: admin and regular users
>* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
>* Menu changes each day (admins do the updates)
>* Users can vote on which restaurant they want to have lunch at
>* Only one vote counted per user
>* If user votes again the same day:
>* If it is before 11:00 we assume that he changed his mind.
>* If it is after 11:00 then it is too late, vote can't be changed
> 
>Each restaurant provides a new menu each day.
> 
>As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - link to Swagger).
>
>P.S.: Make sure everything works with latest version that is on github :)
> 
>P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

Here is a link to Swagger. http://localhost:8080/friendlylunch/swagger-ui.html

**Admin** credentials: username: **admin@gmail.com**, password: **admin**

**User** credentials: username: **user@yandex.ru**, password: **password**