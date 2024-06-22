package com.spare.employee_backend.controller;

import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Response<String> login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public Response<String> register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return authService.register(username, password, email);
    }
}
