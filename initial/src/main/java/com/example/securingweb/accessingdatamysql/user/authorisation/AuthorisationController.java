package com.example.securingweb.accessingdatamysql.user.authorisation;

import com.example.securingweb.accessingdatamysql.user.User;
import com.example.securingweb.accessingdatamysql.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
public class AuthorisationController {

    private UserRepository userRepository;

    @GetMapping("/authentication/register")
    public String showRegistrationForm(Model model) {
        // Создание формы заполнения полей Class:User
        model.addAttribute("user", new User()); // Нужно для обработки на уровне HTML

        return "signup_form";   // переход к signup_form.html
    }

    @PostMapping("/authentication/process_register")
    public String processRegister(User user) {

        //Если имя пользователя уже занято, то выдаём ошибку
        User user1 = userRepository.findByName(user.getName());
        if(user1 != null) {
            return "redirect:/authentication/register?invalid_un=true"; // Перенаправляет по URL адресу
        }

        //Если почта уже занята, то выдаём ошибку
        User user2 = userRepository.findByEmail(user.getEmail());
        if(user2 != null) {
            return "redirect:/authentication/register?invalid_em=true"; // Перенаправляет по URL адресу
        }

        //Кодируем и сохраняем пароль
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Создаём кодировщик
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Кодируем пароль пользователя
        user.setPassword(encodedPassword); // Сохраняем пароль пользователя

        userRepository.save(user); // Сохраняем пользователя в БД

        return "redirect:/?reg=true"; // Перенаправляет по URL адресу
    }

    @GetMapping("/authentication/login")
    public String userLogin(String email, String password, Model model){
        // Если пользователь НЕ авторизован, перенаправляет на страницу авторизации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login_form";
        }

        return "redirect:/app"; // Перенаправляет по URL адресу
    }
}
