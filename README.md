## API автотесты для проектов: [demowebshop](http://demowebshop.tricentis.com/), [demoqa](https://demoqa.com/) and somebank
### Technology stack:
- Java
- Gradle
- Junit5
- Selenide
- REST-Assured
- Allure Report

### Реализованы проверки:

* #### demowebshop:
- [X] Авторизация пользователя, с помощью UI и API;
- [X] Добавление товара в корзину, с помощью UI и API;

* #### demoqa:
- [X] API тесты с разным уровнем логирования;
- [X] API тесты на создание нового пользователя с применением библиотеки JavaFaker;
- [X] API тест с проверкой JSON Schema;

* #### somebank:
- [X] API тест в котором выполняется GET запрос к каждому из сервисов,
полученный ответ десериализуется в объект класса и проверяется, что значение account_id не пустое;

### В тестах используются:
- Спецификации;
- Модели;
- Lombok;
- Проверка ответа с помощью Groovy
  ( тест: 'BookStoreTestsWithModels' );