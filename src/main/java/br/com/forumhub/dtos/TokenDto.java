package br.com.forumhub.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {
    private String token;
    private String tipo; // ex: "Bearer"
}
