package com.wanted.jungproject.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenInfoRequest {
    private String accessToken;
    private String refreshToken;
}
