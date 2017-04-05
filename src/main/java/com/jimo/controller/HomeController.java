package com.jimo.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/")
public class HomeController {

	@PostMapping("/login2")
	public String login(@RequestBody User user) throws ServletException {
		String token = "";
		String name = user.getUsername();
		String pass = user.getPassword();
		if (!"admin".equals(name)) {
			throw new ServletException("找不到该用户");
		}
		if (!"1234".equals(pass)) {
			throw new ServletException("密码错误");
		}
		token = Jwts.builder().setSubject(name).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "base64EncodedSecretKey").compact();
		return token;
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String name, @RequestParam("password") String pass)
			throws ServletException {
		String token = "";
		if (!"admin".equals(name)) {
			throw new ServletException("找不到该用户");
		}
		if (!"1234".equals(pass)) {
			throw new ServletException("密码错误");
		}
		token = Jwts.builder().setSubject(name).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "base64EncodedSecretKey").compact();
		return token;
	}
}
