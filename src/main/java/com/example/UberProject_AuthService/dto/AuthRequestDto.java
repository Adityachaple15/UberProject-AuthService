package com.example.UberProject_AuthService.dto;

import lombok.*;

import java.sql.Struct;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    private String email;
    private String password;
}
