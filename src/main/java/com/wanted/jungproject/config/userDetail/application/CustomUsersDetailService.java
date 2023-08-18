//package com.wanted.jungproject.domain.userDetail.application;
//
//import com.wanted.jungproject.domain.user.domain.Users;
//import com.wanted.jungproject.domain.user.domain.UsersRepository;
//import com.wanted.jungproject.domain.userDetail.domain.CustomUsersDetails;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUsersDetailService implements UserDetailsService {
//
//    private final UsersRepository usersRepository;
//
//    // 로그인 시 authenticate 메소드를 수행할때 DB에서 유저 정보를 조회해오는 loadUserByUsername 메소드가 실행
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users users = usersRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username + "을/를 데이터베이스에서 찾을 수 없습니다."));
//
//        // DB 에 Users 값이 존재한다면 userDetails 객체로 만들어서 리턴
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(users.getRole().toString());
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(grantedAuthority);
//
//        CustomUsersDetails usersDetails = CustomUsersDetails.builder()
//                .id(users.getId())
//                .email(users.getEmail())
//                .password(users.getPassword())
//                .roles(authorities)
//                .build();
//        return usersDetails;
//    }
//}