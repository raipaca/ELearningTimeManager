package com.raipaca.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raipaca.app.domain.User;
import com.raipaca.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String showLoginGet(Model model) throws Exception {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping
	public String showLoginPost(@Valid User user, Errors errors, Model model, HttpSession session) throws Exception {
		if (errors.hasErrors()) {
			return "login";
		}
		if (!userService.isCorrectLoginIdAndPassword(user.getLoginId(), user.getLoginPass())) {
			errors.reject("error.incorrect_id_password");
			return "login";
		}
		user = userService.getUserByLoginId(user.getLoginId());
		session.setAttribute("id", user.getId());
		return "redirect:learning/start";
	}

}
