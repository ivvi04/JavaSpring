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

## Урок 4. Spring MVC. Использование шаблонизатора Thymeleaf

**[Задание](https://github.com/ivvi04/JavaSpring/tree/master/lesson4)**

Базовое задание:

1. Создание базового веб-приложения Spring MVC. 
Начните с создания простого веб-приложения с использованием Spring MVC. 
Это может быть простой сайт, который выводит "Привет, мир!" на главной странице. 
Используйте аннотацию @Controller и @RequestMapping для маршрутизации запросов на эту страницу.
2. Добавление Thymeleaf в проект.
Добавьте Thymeleaf в свое веб-приложение Spring MVC. 
Создайте простую страницу с некоторыми переменными, которые заполняются с помощью модели Spring MVC и 
отображаются на странице с использованием Thymeleaf.
3. Создание формы ввода и обработка данных формы.
Создайте страницу с формой ввода, используя Thymeleaf для рендеринга формы. 
Затем создайте контроллер Spring MVC, который обрабатывает отправку формы и выводит полученные данные. 
Это может быть форма для регистрации или любая другая форма, которая собирает информацию от пользователя.

Задание со звездочкой:

Проект домашнего задания для 2 семинара (CRUD приложение USER SERVICE) переписать:
1. Использовать библиотеку Lomboc:
   - @Data - для полей классов
   - @AllArgsConstructor - для классов с конструкторами
   - @Log - логировать работу всех контроллеров
2. Использовать configuration-processor для работы с настройками приложения:
   - Создать объект инкапсулирующий шаблоны запросов к базе данных H2
   - Использовать аннотации @ConfigurationProperties и @ConfigurationPropertiesScan 
для заполнения полей этого класса
   - Вынести все SQL шаблоны в настройки приложения.

## Урок 5. Spring Data для работы с базами данных

**[Задание](https://github.com/ivvi04/JavaSpring/tree/master/lesson5)**

Текст сложного задания на тему Spring Data JPA:

Ваша команда разрабатывает приложение для управления проектами. Вам нужно реализовать функциональность связывания пользователей с проектами.

Создайте сущность "пользователь" (User), которая будет содержать следующие поля:
- Идентификатор (id) типа Long, генерируемый автоматически при создании записи
- Имя пользователя (username) типа String
- Пароль (password) типа String
- Электронная почта (email) типа String
- Роль (role) типа String

Создайте сущность "проект" (Project), которая будет содержать следующие поля:
- Идентификатор (id) типа Long, генерируемый автоматически при создании записи
- Название проекта (name) типа String
- Описание проекта (description) типа String
- Дата создания (createdDate) типа LocalDate

Создайте абстрактный класс "сущность с связью" (EntityWithRelation), который будет содержать следующие поля:
- Идентификатор (id) типа Long, генерируемый автоматически при создании записи
- Идентификатор связанной сущности (relatedEntityId) типа Long

Создайте сущность "пользователи проекта" (UsersProject), которая наследуется от класса "сущность с связью" и будет содержать следующие поля:
- Идентификатор проекта (projectId) типа Long
- Идентификатор пользователя (userId) типа Long

Создайте интерфейс репозитория (UserRepository), который будет расширять JpaRepository<User, Long>.

Создайте интерфейс репозитория (ProjectRepository), который будет расширять JpaRepository<Project, Long>.

Создайте интерфейс репозитория (UsersProjectRepository), который будет расширять JpaRepository<UsersProject, Long>.

Создайте сервисный класс (UserProjectService), который будет содержать следующие методы:
- public List getUsersByProjectId(Long projectId) - метод, возвращающий список пользователей, связанных с определенным проектом
- public List getProjectsByUserId(Long userId) - метод, возвращающий список проектов, связанных с определенным пользователем
- public void addUserToProject(Long userId, Long projectId) - метод, добавляющий пользователя к проекту
- public void removeUserFromProject(Long userId, Long projectId) - метод, удаляющий пользователя из проекта

Создайте контроллер (UserProjectController), который будет содержать следующие методы:
- public ResponseEntity<List> getUsersByProjectId(Long projectId) - метод, обрабатывающий GET-запрос для получения списка пользователей, связанных с определенным проектом
- public ResponseEntity<List> getProjectsByUserId(Long userId) - метод, обрабатывающий GET-запрос для получения списка проектов, связанных с определенным пользователем
- public ResponseEntity addUserToProject(Long userId, Long projectId) - метод, обрабатывающий POST-запрос для добавления пользователя к проекту
- public ResponseEntity removeUserFromProject(Long userId, Long projectId) - метод, обрабатывающий POST-запрос для удаления пользователя из проекта