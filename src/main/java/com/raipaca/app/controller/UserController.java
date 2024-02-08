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
import com.raipaca.app.service.UserTypeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeService;

	@GetMapping("/add")
	public String addUserGet(Model model) throws Exception {
		model.addAttribute("user", new User());
		model.addAttribute("userType", userTypeService.getAllUserType());
		return "addUser";
	}

	@PostMapping("/add")
	public String addUserPost(@Valid User user, Errors errors, Model model, HttpSession session) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("userType", userTypeService.getAllUserType());
			return "addUser";
		}
		if (userService.getUserByLoginId(user.getLoginId()) == null) {
			model.addAttribute("user", user);
			model.addAttribute("userType", userTypeService.getAllUserType());
			if (user.getName().equals("")) {
				errors.rejectValue("name", "error.user_name_blank");
			}
			errors.reject("error.exist_login_id");
			return "addUser";
		}
		session.setAttribute("user", user);
		session.setAttribute("loginPass", user.getLoginPass());
		userService.addUser(user);
		return "redirect:add/done";
	}

	@GetMapping("/add/done")
	public String addUserDoneGet(Model model, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		user.setLoginPass((String) session.getAttribute("loginPass"));
		model.addAttribute("user", user);
		model.addAttribute("userType", userTypeService.getUserType(user.getTypeId()));
		session.removeAttribute("user");
		session.removeAttribute("loginPass");
		return "addUserDone";
	}

}
