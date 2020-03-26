package com.app.security.jwt;

import com.app.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {
    public JwtUserFactory(){
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                getAuthorities());
    }

    private static List<GrantedAuthority> getAuthorities() {
        List <GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ADMIN");
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

}
