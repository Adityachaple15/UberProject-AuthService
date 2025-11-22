package com.example.UberProject_AuthService.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private Date createdAt;
}
