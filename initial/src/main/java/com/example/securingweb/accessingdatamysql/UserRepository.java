package com.example.securingweb.accessingdatamysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Указывает Hibernate интерфейс - репозиторий, с помощью которого производится
// взаимодействие с таблицей User_Security (User, PrimaryKey.type = Integer)
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //TODO: Понять, почему работает без строчки ниже.
    //@Query("SELECT u FROM user_security u WHERE u.email = ?1")
    User findByEmail(String email); // Автоматически генерируемый Hibernate SQL - запрос к БД
}