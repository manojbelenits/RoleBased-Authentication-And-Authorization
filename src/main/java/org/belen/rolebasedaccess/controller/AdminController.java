package org.belen.rolebasedaccess.controller;

import lombok.RequiredArgsConstructor;
import org.belen.rolebasedaccess.Entity.UserEntity;
import org.belen.rolebasedaccess.reqDto.RegisterRequest;
import org.belen.rolebasedaccess.serviceImpl.AuthenticationServImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationServImpl serv;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Admin";
    }


}