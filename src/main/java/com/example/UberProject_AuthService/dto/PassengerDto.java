package com.example.UberProject_AuthService.dto;

import com.example.UberProject_AuthService.models.Passenger;
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

    public static PassengerDto from(Passenger p){
        PassengerDto result = PassengerDto.builder()
                .id(p.getId().toString())
                .createdAt(p.getCreatedAt())
                .email(p.getEmail())
                .password(p.getPassword())
                .name(p.getName())
                .phoneNumber(p.getPhoneNumber())
                .build();
        return result;
    }
}
