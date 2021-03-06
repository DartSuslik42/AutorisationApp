package com.example.securingweb.accessingdatamysql.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.example.securingweb.accessingdatamysql.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Класс создан для переопределения методов и значений по умолчанию для Class:UserDetails (Spring-Security)
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Предпологается авторизация с помощью поля User.email
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // default = false
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // default = false
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // default = false
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // default = false
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Нужно для запроса поля User.name в users.html
    // Используется другое название, так-как getUsername определено в классе-родителе
    public String getNickname(){
        return user.getName();
    }
}