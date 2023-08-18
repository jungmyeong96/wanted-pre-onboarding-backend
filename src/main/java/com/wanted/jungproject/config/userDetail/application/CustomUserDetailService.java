package com.wanted.jungproject.config.userDetail.application;

import com.wanted.jungproject.config.userDetail.domain.CustomUserDetails;
import com.wanted.jungproject.domain.user.domain.Users;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> users = usersRepository.findByEmail(username);
        if (users.isPresent()) {
            return new CustomUserDetails(users.get());
        }
        return null;
    }
}
