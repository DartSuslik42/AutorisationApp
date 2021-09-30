# AuthorisationApp
## Описание:
Цель проекта - наработа навыков пользования Spring, Hibernate, Maven, MySQL

## TODO лист:
Общее:
- [x] Создать макет приложения
- [x] Настроить права доступа в зависимости от роли пользователя

База данных:
- [x] Настроить подключение к базе данных с помощью MySQL
- [x] Реализовать функционал простейших запросов в базу данных /app/users/{id}
- [x] Добавить роли пользователей на уровне базы данных USER, ADMIN, EDITOR, CREATOR ...
- [ ] Добавить функционал удаления, изменения.
- [ ] Добавить назначение ролей по умолчанию.
- [ ] Добавить назначение ролей с помощью WEB-страницы

Авторизация:
- [x] Добавить авторизацию и регистрацию с использованием SpringSequrity 
- [x] Сделать переадресацию на рукописную страницу авторизации /login_form
- [x] Переход на страницу авторизации невозможен для авторизированных прользователей
- [x] Добавить обработку ошибок в поле регистрации 
- [x] Добавить сообщение об успешной регистрации

# **Полезные ссылки:**
1. [Setup simple SpringSecurity Application](https://spring.io/guides/gs/securing-web/)
2. [Basic MySQL-commandline commands](https://dev.mysql.com/doc/mysql-getting-started/en/)
3. [How to make custom Login/Register Application with connection to MySQL database](https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial)
4. [How to make custom Login page](https://www.youtube.com/watch?v=yoTohM2jYhs&ab_channel=JavaBrains)
5. [How to prevent user from going back to login page if already logged in](https://www.codejava.net/frameworks/spring-boot/prevent-user-from-going-back-to-login-page-if-already-logged-in)
6. [Thymeleaf: if / unless / switch statements](https://habr.com/ru/post/351304/)
