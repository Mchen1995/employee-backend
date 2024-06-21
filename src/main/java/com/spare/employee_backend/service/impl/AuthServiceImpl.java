package com.spare.employee_backend.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.spare.employee_backend.model.Response;
import com.spare.employee_backend.model.User;
import com.spare.employee_backend.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    /**
     * 用户表
     */
    public static final List<User> USERS = new ArrayList<>();
    // 默认初始用户
    static {
        USERS.add(new User("admin", "admin"));
    }
    @Override
    public Response<String> login(String username, String password) {
        if (StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password)) {
            return new Response<>(false, "用户名或密码为空", null);
        }
        boolean noneMatch = USERS.stream().noneMatch(user -> user.getUsername().equals(username));
        if (noneMatch) {
            return new Response<>(false, "用户"+username+"不存在", null);
        }

        List<User> collect = USERS.stream().filter(user -> user.getUsername().equals(username)).toList();
        if (!collect.get(0).getPassword().equals(password)) {
            return new Response<>(false, "用户名或密码错误", null);
        }
        return new Response<>(true, "登录成功", null);

    }
}
