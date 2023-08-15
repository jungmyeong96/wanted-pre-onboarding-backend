package com.wanted.jungproject.domain.user.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(length = 60)
    private String address;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 80)
    private String passwrod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String name, String address, String email, String passwrod, Role role) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.passwrod = passwrod;
        this.role = role;
    }

}
