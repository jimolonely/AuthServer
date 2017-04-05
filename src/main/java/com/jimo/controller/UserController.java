package com.jimo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/success")
	public String success() {
		return "恭喜您登录成功";
	}

	@GetMapping("/getEmail")
	public String getEmail() {
		return "xxxx@qq.com";
	}
}
