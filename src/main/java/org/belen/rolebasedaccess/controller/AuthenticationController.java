package org.belen.rolebasedaccess.controller;


import lombok.RequiredArgsConstructor;
import org.belen.rolebasedaccess.Entity.UserEntity;
import org.belen.rolebasedaccess.reqDto.LoginRequest;
import org.belen.rolebasedaccess.reqDto.LoginResponse;
import org.belen.rolebasedaccess.reqDto.RegisterRequest;
import org.belen.rolebasedaccess.serviceImpl.AuthenticationServImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

   private final AuthenticationServImpl serv;



    @PostMapping("/login")
    public ResponseEntity<?> loginEndpoint(@RequestBody LoginRequest request){

        LoginResponse loginResponse = serv.loginMethod(request);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEndpoint(@RequestBody RegisterRequest request){

        UserEntity theUser = serv.createTheUser(request);

        return new ResponseEntity<>(theUser, HttpStatus.OK);
    }
}