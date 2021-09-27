package com.example.securingweb.accessingdatamysql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor /*  Создаёт конструктор без аргументов.
                       Нужен для создания формы заполнения & получения данных пользователя
                       со страницы регистрации.
                    */
@Getter // Создаёт геттеры
@Setter // Создаёт сеттеры
@ToString // Создаёт метод .toString
@Entity // Указывает Hibernate, что нужно создать таблицу с полями этого класса
@Table(name = "user_security") // Указывает название таблицы
/* Класс User содержит поля:
      id          "ID"        - primary_key,
      name        "NAME"      - unique, notnull,
      email       "EMAIL"     - unique, notnull,
      password    "PASSWORD"  - notnull
      userRole    "ROLE"      - default = "UserRole.USER"
*/
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}