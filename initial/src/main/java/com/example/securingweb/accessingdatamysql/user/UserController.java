package com.example.securingweb.accessingdatamysql.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("")
    public String viewHomePage(){
        return "index";     // переход к index.html
    }

    @GetMapping("/app")
    public String appPage() {
        return "app_page";
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