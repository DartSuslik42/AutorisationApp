package com.example.securingweb.accessingdatamysql;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor /* Конструктор всех полей класса.
                       Добавлен что-бы создать DependencyInjection в поле UserRepository.
                       Внедрить зависимость можно без этой аннотации, добавя конструктор класса:

                       @Autowired // Указывает Hibernate, что в параметры конструктора
                                  // нужно передать @Bean UserRepository
                       public UserController(UserRepository userRepository){
                            this.userRepository = userRepository;
                       }
*/
@Controller // Указывает Hibernate на то, что этот класс является @Bean
//В классах - контроллерах @Controller связывают URL адреса и запросы.
//логика запросов реализуется в классах - сервисах @Service
public class UserController {

    private UserRepository userRepository;

    //TODO: Сделать переадресацию на рукописную страницу авторицзации login.html

    @GetMapping("")
    public String viewHomePage(){
        return "index";     // переход к index.html
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Создание формы заполнения полей Class:User
        model.addAttribute("user", new User()); // Нужно для обработки на уровне HTML

        return "signup_form";   // переход к signup_form.html
    }

    // TODO: при переходе на index.html высвечивать надпись об успешной регистрации
    // TODO: при некоректно ведённых данных переходить на signup_form.html с объяснением ошибки
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Создаём кодировщик
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Кодируем пароль пользователя
        user.setPassword(encodedPassword); // Сохраняем пароль пользователя

        userRepository.save(user); // Сохраняем пользователя в БД

    //  return "register_success"; // переход к register_success.html
        return "index"; // переход к index.html
    }

    @GetMapping("/app/users")
    public String listUsers(Model model) {
        List<User> listUsers; // Возвращает всё содержимое базы данных
        listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers); // Нужно для обработки на уровне HTML

        return "users"; // переход к users.html
    }

    @GetMapping("/app/users/{id}")
    public String getUserByID(@PathVariable Integer id, Model model) {

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            // Если пользователь найден
            List<User> users = new ArrayList<User>();
            users.add(user.get());
            model.addAttribute("listUsers",users);
            return "users";
        }
        else {
            // Если пользователь не найден
            model.addAttribute("listUsers", new ArrayList<>());
            return "users";
        }
    }

}