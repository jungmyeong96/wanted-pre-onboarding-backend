package com.wanted.jungproject.domain.user.dto;

import com.wanted.jungproject.domain.user.domain.Role;
import com.wanted.jungproject.domain.user.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor()
@Data
public class UserSignUpRequest {

    private int id;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    @Pattern(regexp = ".*@.*", message = "이메일 형식이 올바르지 않습니다.")
//    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @Pattern(regexp = ".{8,}", message = "비밀번호는 8자 이상이어야 합니다.")
    private String password;

    private String address;

    @Builder
    public UserSignUpRequest(String name, String email, String password, String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Users toEntity() {
        return Users.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .address(this.address)
                .role(Role.USER)
                .build();
    }
}
