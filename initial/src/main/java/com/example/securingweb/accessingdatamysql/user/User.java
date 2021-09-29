package com.example.securingweb.accessingdatamysql.user;

import com.example.securingweb.accessingdatamysql.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
      enabled     "ENABLED"   - default = "false"
      roles       "ROLES"     - Tables : users <-> users_roles <-> roles (Many to many);
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

    //TODO: разобраться с написанным кодом. https://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-association-annotations-example

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}