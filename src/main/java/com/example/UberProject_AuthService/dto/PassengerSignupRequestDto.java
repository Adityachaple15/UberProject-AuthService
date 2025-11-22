package com.example.UberProject_AuthService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {

    String email;
    String password;
    String phoneNumber;
    String name;
}
