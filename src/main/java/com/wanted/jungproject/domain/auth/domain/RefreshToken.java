package com.wanted.jungproject.domain.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Builder
@Entity
public class RefreshToken {

    @Id
    @Column(name = "rt_key" )
    private String key;

    @Column(name = "rt_value")
    private String value;           // Refresh Token String

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}
