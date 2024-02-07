# Фреймворк Spring (семинары)

## Урок 1. Системы сборки Maven и Gradle для разработки Java приложений

**[Задание](https://github.com/ivvi04/JavaSpring/tree/master/lesson1)**

Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код, использующий эти зависимости.

Пример решения:
1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
3. Создайте класс Person с полями firstName, lastName и age.
4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.

## Урок 2. Основы Spring. Spring Boot

**[Задание](https://github.com/ivvi04/JavaSpring/tree/master/lesson2)**

Базовое задание:
Добавить в простое CRUD веб-приложение, которое было разработано на семинаре функцию удаления данных о пользователе:
1. В класс UserRepository добавить метод public void deleteById(int id)(подсказка: SQL запрос "DELETE FROM userTable WHERE id=?", метод jdbc.update) - удаления записи пользователя из БД по ID. 
2. В класс UserService добавить метод public void deleteById(int id)(подсказка: в нем вызывается метод userRepository.deleteById) - удаление пользователя через репозиторий. 
3. В класс UserController добавить метод public String deleteUser(@PathVariable("id") int id)(с аннотацией: @GetMapping("user-delete/{id}")) (подсказка: в нем вызывается метод userService.deleteById) - перехват команды на удаление студента от браузера.

Если задание выполнено верно, то при запуске приложения по адресу http://localhost:8080/users будет работать кнопка удаления пользователя по ID.

Задание "со звездочкой":
Реализовать метод обновления данных о пользователе.
- @GetMapping("/user-update/{id}")
- @PostMapping("/user-update")
- User update(User user)
- User getOne(int id)

## Урок 3. Использование Spring для разработки серверного приложения

**[Задание](https://github.com/ivvi04/JavaSpring/tree/master/lesson3)**

1) В класс RegistrationService добавить поля UserService, 
NotificationService(добавить в IOC контейнер аннотацией @Autowired или через конструктор класса)
2) Разработать метод processRegistration в котором:
- создается пользователь из параметров метода
- созданный пользователь добавляется в репозиторий
- через notificationService выводится сообщение в консоль
3) В TaskController добавить обработчики filterUsersByAge()(Подсказка @GetMapping("/filter/{age}")) и 
calculateAverageAge (Подсказка @GetMapping("/calc"))
4) В методе filterUsersByAge параметр age получать через аннотацию @PathVariable

Задание со звездочкой
1) В классе UserController добавить обработчик userAddFromParam извлекающий данные для создания пользователя 
из параметров HTTP запроса
2) Перенести репозиторий проекта с List<User> на базу данных H2