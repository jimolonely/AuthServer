package com.jimo.controller;

import com.jimo.model.User;
import com.jimo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

/**
 * @author jimo
 * @func controller
 * @date 2018/8/24 22:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * @func 测试时先用死的用户名密码，请求使用JSON格式数据
     * @author wangpeng
     * @date 2018/8/24 22:45
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) throws ServletException {
        if (!"admin".equals(user.getUsername())) {
            throw new ServletException("no such user");
        }
        if (!"1234".equals(user.getPassword())) {
            throw new ServletException("wrong password");
        }
        return JwtUtil.getToken(user.getUsername());
    }

    @GetMapping("/success")
    public String success() {
        return "login success";
    }
}
