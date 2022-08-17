# Friendly Lunch
[![Anton Petrunov's FrienlyLunch stats](https://github-readme-stats.vercel.app/api?username=anton-petrunov&exclude_repo=topjava,topjava-1&show_icons=true&custom_title=Anton&#32;Petrunov's&#32;FriendlyLunch&#32;stats)](https://github.com/anton-petrunov/friendlylunch)[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=anton-petrunov&layout=compact&exclude_repo=topjava,topjava-1)](https://github.com/anton-petrunov/friendlylunch)

Это REST API beckend-приложение. Оно предназначено для просмотра и голосования за меню ресторанов, которые админ предоставил на дату следующего обеда.

Как админ вы совершаете CRUD-операции с ресторанами и их  меню, с блюдами этих меню и пользователями.

Как пользователь вы хотите пообедать вместе с друзьями. Чтобы совместно выбрать ресторан, вы просматриваете меню на дату следующего обеда. Доступны только те рестораны, которые предоставили меню с блюдами на эту дату. Затем вы вместе с другими пользователями голосуете за желаемое меню доступного ресторана. Победитель — это ресторан, в котором пройдёт дружеский обед.
## Technologies
✔Java Core, ✔Spring, ✔Maven, ✔Hibernate, ✔Git, ✔Spring Data JPA, ✔Spring Security, ✔HSQLDB, ✔IntelliJ IDEA, ✔Tomcat, ✔GitHub, ✔Swagger, ✔XML, ✔Spring MVC, ✔Java8 Stream API, ✔Java Collections, ✔JDK17, ✔REST, ✔JSON, ✔Jackson, ✔Ubuntu, ✔Java DateTime API, ✔Trello, ✔XMind
## Getting Started
Эти инструкции помогут вам запустить копию проекта на вашем локальном компьютере для целей разработки и тестирования. Инструкции по деплою на локальный сервер находятся в разделе [Deployment](https://github.com/anton-petrunov/friendlylunch/edit/master/README.md#deployment)
### Prerequisites
В проекте используется встроенная база данных HSQLDB, поэтому необходимых условий не требуется.
### Installing to IntelliJ IDEA
1. Создайте новый новый проект из системы контроля версий

    ![[img.png](https://user-images.githubusercontent.com/97449490/184336748-0b700f84-ddf2-4627-a83d-d6fe28e203a7.png)](https://user-images.githubusercontent.com/97449490/184336748-0b700f84-ddf2-4627-a83d-d6fe28e203a7.png)

2. Скопируйте ссылку на проект
- либо из GitHub `Code` → copy URL

    ![[image](https://user-images.githubusercontent.com/97449490/184353612-7e795d8d-0909-44af-b0c7-2cac0fbcb4c9.png)](https://user-images.githubusercontent.com/97449490/184353612-7e795d8d-0909-44af-b0c7-2cac0fbcb4c9.png)
    
- либо вот она 
```
https://github.com/anton-petrunov/friendlylunch
```
3. Вставьте ссылку в поле диалога
4. Нажмите `Clone`
5. Откройте диалоговое окно `Project Settings` через `File` → `Progect Structure...`
    - В диалоговом окне `Project Settings` во вкладке `Project`  в поле `SDK` установите версию библиотеки `17` любого знакомого вам Вендора
    - В поле `Language Level` установите `SDK default` (17 - Sealed types, always-strict floating-point semantics)
    
    ![](https://lh3.googleusercontent.com/EQTjkGWe2PgAZYYapQMgKlFILxQb4nb_VhoLU9rfWDhJP35E_IEZB_b2Sie5rA-7ooLHeAwveuc9S6aw5f_s1Lr1UryMYhCZyjpM2WPsBRRkeKcCb8H_d0_HFHICSSwZ-O2hKkVQVhGm0hcjU935Ugg)
## Built With
Проект собирается с помощью [Maven](https://maven.apache.org/). После успешного клонирования — перезагрузите Maven:
- либо через всплывающую кнопку `Load Maven Changes`

     ![[image](https://user-images.githubusercontent.com/97449490/184360490-febacb85-0341-4fce-8428-ab46df6d0c98.png)](https://user-images.githubusercontent.com/97449490/184360490-febacb85-0341-4fce-8428-ab46df6d0c98.png)
- либо через боковую панель Maven → кнопка `Reloal All Maven Projects`

    ![[image](https://user-images.githubusercontent.com/97449490/184366181-f6c0274a-f20e-4ddf-97ff-502a32b9b885.png)](https://user-images.githubusercontent.com/97449490/184366181-f6c0274a-f20e-4ddf-97ff-502a32b9b885.png)

**Для *дальнейшего  деплоя* необходимо сделать `clean` и `package`:**
- боковая панель Maven → `Lifecycle` → `clean`

        ![[image](https://user-images.githubusercontent.com/97449490/184365418-4516c352-6525-4551-a052-c031c01714e7.png)](https://user-images.githubusercontent.com/97449490/184365418-4516c352-6525-4551-a052-c031c01714e7.png)
    
- боковая панель Maven → `Lifecycle` → `package`

    ![[image](https://user-images.githubusercontent.com/97449490/184365727-af4b14c8-0f05-4f97-acc7-af5402620777.png)](https://user-images.githubusercontent.com/97449490/184365727-af4b14c8-0f05-4f97-acc7-af5402620777.png)
## Deployment
В проекте используется Tomcat 9. 
> ### На совместимость с Tomcat 10 я не проверял.
1. Перейдите в меню редактирования конфигураций запуска `Edit Configurations`

    ![](https://lh5.googleusercontent.com/y0iY6uHu2C-9SjYFZx44sjZIX22EyKKKjNPVhC02OQO8_vVYDL9u93TO3TZs85eQPap8IeVqo5oegl4mGWI23Ivduhkf6CSu8jjUYdesCkb1zeqVJn0xpN2pNzB2bTN8Lmlrtv0FA-kL6kRD3hC0yUc)

1. Нажмите на `Add New Configuration`

    ![](https://lh6.googleusercontent.com/DdukmlDFbvIRdfXZDvVBcTYVLFPWMk0ed-EvFmtfannQUfDrzJ9VRNj5GsjUZDZjAGKd0cL8rY2OgVhOqmGWk06WE-aCU24eKNlsdzP-K2bX1Kkb97pZC46zWMls3QFJ4XK09fo29ZGJTrFYX9t2-9M)

1. Выберите  `Add New Configuration` → `Tomcat Server` → `Local`

    ![](https://lh4.googleusercontent.com/pOfCxb3xky6hZnyRwvpS0BqjxCKHxo5z1YfFRMl2NmtyGwhoumokuW8zJIVRAdQzBTg4Sx7QEAuIlbQ945mHMn1XDBZEnpVuNBzwspJ1Xu13LYh2rZz5ksSYS7-5SNHflBrxSgY6ib-vjSLZAZ2gD9o)

1. Вы попадёте в `Run/Debug Configurations` создаваемого вами локального сервера Tomcat
1. На вкладке `Server` в поле `Application Server` выберите необходимую версию Tomcat

    ![](https://lh5.googleusercontent.com/vsVUOHauIycGU4eUIfeNMwufYYEmntFNNvzLgLw86sCdBuXtn35DJezfE-oexndj3At0qCDBv9SjXktubZMcNH5-NKOoBeGNHKSxX6DQxDFjpzA9NXo_KMPd73fcUIrGWbmXvNuB4tVUwr7griTRMgM)

    > #### Если у вас не установлен Tomcat, но вы дочитали до этого места... То с вероятностью больше 99% вы и сами знаете, что делать дальше и как подключить Tomcat к проекту 🙂
1. Далее перейдите на вкладку `Deployment` и нажмите в области `Deploy at the server startup` на кнопку `Add`

    ![](https://lh5.googleusercontent.com/TwPGkcF89EV0coyZikr3hKrbojksz0q52azLmhCFe9J2EjBPNHlgdajNMkhiNXCt6J1-5Bw6wJmxUkHSrgnKdISXkUwcHTwH5q5GfOPFpXO8uMV61BYR65w195QFlHjf9rFlou4jYs3jlZhgGJifa_Y)

1. Выберите тип объекта для деплоя `Artifact`

    ![](https://lh5.googleusercontent.com/TvNjT9eB4zIA_6VP8n0zbslGeQ-wh9NQgBX4Y_jDVqkvY5gRTXQBbswPOrVpTj8dN22lCMcoKU0UME914dsCzHLZ0_aRAVx-JKj_1-fsyy-vv4hy9haQYaHF_-MsKGFmA9ikQ8YScsINTMnLj9Xpq1g)

1. В диалоговом окне `Select Artifact to Deploy` выберите `friendlylunch:war exploded` и нажмите `OK`

    ![](https://lh3.googleusercontent.com/vIekm0a-HrPm9ICi77KznSVxLKSENmfYdLAo6yY64LYU1rVhy5nVCUEE4uxncrwnVZxlgbD4Qu_1t97jY_h1oMvXjlG1VEQvnfGmelwrubaoXxbZkGy6Yba4tvTeUw2JS4lU9zmlZbESrpP94ynWjRE)

1. **ВАЖНО! Настройку `Application context:` измените на**
    ```
    /friendlylunch
    ```
    ![](https://lh5.googleusercontent.com/iFdLY1lf339H3JKh96Cq93GjWmHbNJ9O1kT6oNi4mtFSHaXNu_bv_z2S0nZTbkxH86xMl5CifwNO0HENr3T4ha_eKoBMNtksgvS5mjMw03U3GiYtg3e2uG43Nzz_jPwkqcTi7E7nOao76amXzV5E6a0)

1. После этого на вкладке `Server` в областях `URL` и `Tomcat Server Settings` ничего менять не нужно. Вкладка `Server` должна иметь такой вид:

    ![](https://lh5.googleusercontent.com/L8S9NIun0b8QrGH5szrH84YLF_KiJBn2sq5tMCX7H7o6yKNY1LdQIfHN_S8ojx4jcSwHzzSHuXCLkMPM75YQqJxY-hdoXekaKEV2gL5O4EHgAqKGXs-fbPHZgKKeXMULacHnvkLx_C3OpnuFclC6up4)

1. Нажмите `OK`
## Using
Вы можете подёргать API через Swagger UI по ссылке http://localhost:8080/friendlylunch/swagger-ui.html

![image](https://user-images.githubusercontent.com/97449490/185122496-b1d85e2a-4b7f-4e2a-8cbd-7ea4a5ebfd95.png)

### Логин и пароль для доступа к действиям предустановленного админа:
```
admin@gmail.com
``` 
```
admin
```
### Логин и пароль для доступа к действиям предустановленного пользователя:
```
user@yandex.ru
``` 
```
password
```
## Code with hardest functionality realized by me
### Главная сложность
В моём REST API я использую доменную модель с *двумя* уровнями вложенности. `Dishes` — это ***подпод***ресурс, `menus` — это ***под***ресурс, а `restaurants` — это ресурс:
```
http://localhost/friendlylunch/restaurants/100005/menus/100009/dishes/100013
```
```
http://localhost/friendlylunch/restaurants/{restaurantId}/menus/{menuId}/dishes/{dishId}
```
Также для своего проекта я выбрал "чистый" Spring MVC.

В результате таких исходных данных я не нашёл типового решения для одновременной валидации **подподресурса** `dishes`, **подресурса** `menus` и **ресурса** `restaurants`.

Также я не нашёл типового решения для вывода ошибок о том, что же именно не найдено: `dishes`, `menus` или `restaurants`.
### Решение
> #### Создал что-то типа вложенной валидации с вложенной обработкой NotFoundException
#### Menu
Для валидации и отображения ошибок при неправильном вводе *ресурса* `restaurantId` 
- В `AbstractMenuController` вызываю метод `get` класса `AbstractRestaurantController`, который уже содержит в себе валидацию и вывод ошибок для *ресурса* `restaurantId`

> #### Класс [`AbstractMenuController`](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java) строки [25](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L25), [31](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L31), [46](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L46), [54](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L54), [60](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L60), [66](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L66), [72](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L72), [78](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L78), [86](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L86), [94](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/menu/AbstractMenuController.java#L94)
#### Dish
Для валидации и отображения ошибок при неправильном вводе *ресурса* `restaurantId` и *подресурса* `menuId`

- В `AbstractDishController` вызываю метод `get` класса `AbstractMenuController`, который уже содержит
    - Как валидацию и вывод ошибок для *подресурса* `menuId`,
    - Так и валидацию и вывод ошибок для *ресурса* `restaurantId`

> #### Класс [`AbstractDishController`](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java) строки [25](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L25), [31](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L31), [46](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L46), [54](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L54), [60](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L60), [66](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L66), [72](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L72), [80](https://github.com/anton-petrunov/friendlylunch/blob/329aadc2845bfb07f7cfd37c0df7f3fbd5a4ab84/src/main/java/com/github/friendlylunch/web/dish/AbstractDishController.java#L80)
## Proud of
- Получилась богатая и честная история коммитов
- Совершил достаточно ошибок и с честью исправил их
- Каждую строчку написал своими руками
## Running the tests
В текущей версии тестов нет
## Contributing
Похоже, что сейчас вы дошли до пункта “Вклад” и размышляете, клонировать мой проект, или нет.

Если вы примете решение сделать это, то до реального “вклада” в моё развитие останется действительно один шаг. Пожалуйста, пришлите мне ваши замечания:

- по коду
- по реализации
- по лучшим решениям
- по общим практикам
- по вашим вариантам
- по рекомендуемым библиотекам

Используйте для этого `Pull Request` или `Issues` и любой удобный для вас способ исправлений. Вы и сами лучше меня знаете, как это работает.

Ваше время действительно стоит дорого. И если вы всё же решили потратить его на мой проект (даже если вам “спустили его сверху”), то прислать несколько комментариев будет для вас нетрудно и быстро.

А я в свою очередь получу компетентный взгляд на свой проект от опытного и старшего товарища. И это сделает меня по-настоящему счастливым 🙂 
## Authors
- Anton Petrunov — Initial work — [anton-petunov](https://github.com/anton-petrunov)
## License
This project is licensed under the [X11 License](https://spdx.org/licenses/X11.html)
## Acknowledgments
- Billie Thompson — [README-Template.md](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) 
- [Мой краткий конспект про README от Андрея Латышева](https://docs.google.com/document/d/1BajqchLTdN1eBVGS44C16UWO6puVKuGpe1tKf5PSPt8/edit?usp=sharing) из видео про Java Code Style
- Dan Shahin — [README images in Github](https://youtu.be/nvPOUdz5PL4)
- [Техническое задание](https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md#technical-requirement) на это приложение
- Anurag Hazra — [github-readme-stats](https://github.com/anuraghazra/github-readme-stats)
- Татьяна Бабичева — [Как создать крутой профиль на GitHub? Оформление репозиториев и README. Портфолио разработчика](https://youtu.be/xWHuw_1G-KA)
- Татьяна Бабичева — [Продающе оформленный GitHub](https://github.com/EnjiRouz)
- Пётр Арсентьев — [GitHub Profile вместо резюме программиста](https://youtu.be/nEnuP2NmMA4)
