package com.example.UberProject_AuthService.controllers;

import com.example.UberProject_AuthService.dto.AuthRequestDto;
import com.example.UberProject_AuthService.dto.AuthResponseDto;
import com.example.UberProject_AuthService.dto.PassengerDto;
import com.example.UberProject_AuthService.dto.PassengerSignupRequestDto;
import com.example.UberProject_AuthService.models.Passenger;
import com.example.UberProject_AuthService.services.AuthService;
import com.example.UberProject_AuthService.services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    @Value("${cookie.expiry}")
    private  int cookieExpiry;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthController(AuthService authService,AuthenticationManager authenticationManager,JwtService jwtService){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response = authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response){
        System.out.println("recivied" + authRequestDto.getEmail() +" "+ authRequestDto.getPassword());
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequestDto.getEmail(),
                                authRequestDto.getPassword()
                        )
                );
        if(authentication.isAuthenticated()){
            String jwtToken = jwtService.createToken(authRequestDto.getEmail());

            ResponseCookie cookie = ResponseCookie.from("JwtToken",jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .maxAge(cookieExpiry)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());

            return new ResponseEntity<>(AuthResponseDto.builder().success(true).build(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Auth not Successful",HttpStatus.OK);
        }
    }
}
