# Городской проводник
Летняя стажировка Simbirsoft.

### Задание
Описание идеи: 
приложение с информацией для путешественников по России о каждом городе с фото. 
Указаны стоимость проезда по городу, отели/гостиницы с возможностью бронирования, 
карта города и метро, краткое описание достопримечательностей. 
Арендодатели посуточного жилья могут размещать свои объявления. 
Афиша массовых мероприятий. Рейтинг мест питания.

[Теническое задание](files/task.docx)

### Tools and Technologies Used
* Java 11
* Maven
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL 12
* Flyway
* Thymeleaf
* Bootstrap 5

### Запуск приложения
Для запуска приложения на локальном хосте необходимо создать базу данных **cityexplorer** (PostgreSQL 12).

Для доступа к базе данных используются параметры по умолчанию: 
имя пользователя ***postgres***, пароль ***postgres***

После запуска приложения в базе данных будет создан один пользователь: 
***admin*** c паролем ***123456*** 

### Полезные ссылки
Гео
* [Получить координаты по адресу](https://snipp.ru/tools/address-coord)

Архитектура
* [Чистая Архитектура для веб-приложений](https://habr.com/ru/post/493430/)
* [4 типа архитектуры программного обеспечения](https://nuancesprog.ru/p/12019/)
* [Best Practice - Multi Layer Architecture and DTOs](https://stackoverflow.com/questions/23308241/best-practice-multi-layer-architecture-and-dtos)
* [Should services always return DTOs, or can they also return domain models?](https://stackoverflow.com/questions/21554977/should-services-always-return-dtos-or-can-they-also-return-domain-models)
* [Spring DTO-DAO (Resource - entity) mapping goes in which application layer: Controller or Service?](https://stackoverflow.com/questions/31644131/spring-dto-dao-resource-entity-mapping-goes-in-which-application-layer-cont/35798539#35798539)

Security  
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Защита методов – аннотация @PreAuthorize](https://sysout.ru/zashhita-metodov-annotatsiya-preauthorize/)
* [Common Built-In Expressions](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#el-common-built-in)

Работа с базами данных
* [Spring Boot FlyWay: миграции БД, профиль пользователя](https://www.youtube.com/watch?v=ArM7nCys4hY&list=PLU2ftbIeotGpAYRP9Iv2KLIwK36-o_qYk&index=12)
* [Добавляем БД PostgreSQL к RESTful сервису на Spring Boot. Часть 2](https://javarush.ru/groups/posts/2582-dobavljaem-bd-k-restful-servisu-na-spring-boot-chastjh-2)
* [Урок 3-5: Расширенное использование Spring Data JPA](https://russianblogs.com/article/3640761048/)
* [Java Code Examples for javax.persistence.criteria.Order](https://www.programcreek.com/java-api-examples/index.php?api=javax.persistence.criteria.Order)

Thymeleaf
* [Tutorial: Thymeleaf + Spring](https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html)
* [Введение в Thymeleaf](https://sysout.ru/vvedenie-v-thymeleaf/)
* [HTML Form Handling in Thymeleaf and Spring Boot](https://attacomsian.com/blog/spring-boot-thymeleaf-form-handling)
* [Руководство: Thymeleaf + Spring. Часть 3](https://itnan.ru/post.php?c=1&p=437658)

Разное
* [Assert a good practice or not?](https://stackoverflow.com/questions/2440984/assert-a-good-practice-or-not)
* [How to globally configure `@DateTimeFormat` pattern in Spring Boot?](https://stackoverflow.com/questions/45440919/how-to-globally-configure-datetimeformat-pattern-in-spring-boot)
* [Bootstrap 5](https://bootstrap5.ru/docs/getting-started/introduction)