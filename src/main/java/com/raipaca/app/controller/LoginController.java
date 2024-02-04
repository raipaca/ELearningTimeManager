package com.raipaca.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raipaca.app.domain.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class LoginController {

//	@Autowired
//	private StudentService studentService;

	@GetMapping
	public String showLoginGet(Model model) throws Exception {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping
	public String showLoginGet(@Valid User user, Errors errors, Model model, HttpSession session) throws Exception {
		if (errors.hasErrors()) {
			return "login";
		}
//		if (!studentService.isCorrectIdAndPassword(student.getLoginId(), student.getLoginPass())) {
//			errors.rejectValue("statusMessage", "error.incorrect_id_password");
//			return "login-student";
//		}
//		session.setAttribute("loginId", student.getLoginId());
		return "redirect:rental";
	}

}
