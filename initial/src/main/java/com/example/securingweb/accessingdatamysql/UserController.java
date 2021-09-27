package com.example.securingweb.accessingdatamysql;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Создаём кодировщик
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Кодируем пароль пользователя
        user.setPassword(encodedPassword); // Сохраняем пароль пользователя

        userRepository.save(user); // Сохраняем пользователя в БД

        return "register_success"; // переход к register_success.html
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll(); // Возвращает всё содержимое базы данных
        model.addAttribute("listUsers", listUsers); // Нужно для обработки на уровне HTML

        return "users"; // переход к users.html
    }

//    @GetMapping(path="/users/{id}")
//    public @ResponseBody ResponseEntity<User> getUserByID(@PathVariable Integer id) {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()) {
//            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
//        }
//        else {
//            throw new NoSuchElementException();
//        }
//    }

}