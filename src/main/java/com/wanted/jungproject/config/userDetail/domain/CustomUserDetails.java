package com.wanted.jungproject.config.userDetail.domain;

import com.wanted.jungproject.domain.user.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Builder
public class CustomUserDetails implements UserDetails, OAuth2User {

    private Users users; //콤포지션
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return users.getRole().getKey();
            }
        });
        return collect;
    }

    //일반 로그인
    public CustomUserDetails(Users users) {
        this.users = users;
    }

    //OAuth 로그인
    public CustomUserDetails(Users users, Map<String, Object> attributes) {
        this.users = users;
        this.attributes = attributes;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getName();
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

    @Override
    public String getName() {
        return null;
    }
}
