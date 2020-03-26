package com.app.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Integer id;
    private final String userName;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authority;

    public JwtUser(Integer id, String userName, String password, String email,  Collection<? extends GrantedAuthority> authority) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority ;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
