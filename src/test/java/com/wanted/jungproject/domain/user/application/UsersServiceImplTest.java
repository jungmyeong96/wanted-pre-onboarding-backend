//package com.wanted.jungproject.domain.user.application;
//
//import com.wanted.jungproject.domain.posts.application.PostsServiceImpl;
//import com.wanted.jungproject.domain.user.domain.Users;
//import com.wanted.jungproject.domain.user.domain.UsersRepository;
//import com.wanted.jungproject.domain.user.dto.UserSignUp;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//
//@ExtendWith(MockitoExtension.class)
//class UsersServiceImplTest {
//    private static final String EMAIL = "test@email.com";
//    private static final String PASSWORD = "12345ASDFASD";
//    private static final String NAME = "김정호";
//    private static final String ADDRESS = "우리집이야?";
//
//    @InjectMocks
//    private UsersServiceImpl usersServiceImpl;
//
//    @Mock
//    private UsersRepository usersRepository;
//
//    @Mock
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Test
//    @DisplayName("[서비스 테스트] 비밀번호는 암호화되어야 한다.")
//    void hashPassword() throws Exception {
//        // given
//        UserSignUp user = createSignUpRequest();
//        Users users = user.toEntity();
//
//        Long fakePostsId = 1l;
//        ReflectionTestUtils.setField(users, "id", fakePostsId);
//
//        String encrypedPassword = bCryptPasswordEncoder.encode(PASSWORD);
//
//        //mocking
//        given(usersRepository.save(any()))
//                .willReturn(users);
//        given(usersRepository.findById(fakePostsId))
//                .willReturn(Optional.ofNullable(users));
//        // when
//        Long userId = usersServiceImpl.signUp(user);
//
//
//        Optional<Users> newUser = usersRepository.findById(userId);
//
//        // then
//        System.out.println("newUser pw = " + newUser.get().getPassword());
//        assertThat(newUser.get().getPassword()).isNotEqualTo(encrypedPassword);
//    }
//
//
//    private UserSignUp createSignUpRequest() {
//        return UserSignUp.builder()
//                .email(EMAIL)
//                .password(PASSWORD)
//                .name(NAME)
//                .address(ADDRESS)
//                .build();
//    }
//}