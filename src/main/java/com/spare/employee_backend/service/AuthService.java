package com.spare.employee_backend.service;

import com.spare.employee_backend.model.Response;

public interface AuthService {
    Response<String> login(String username, String password);
    Response<String> logout();

    Response<String> register(String username, String password, String email);
}
