package com.raipaca.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raipaca.app.domain.User;
import com.raipaca.app.service.UserTypeService;

@Controller
@RequestMapping("/user")
public class UserController {

//	@Autowired
//	private UserService userService;

	@Autowired
	private UserTypeService userTypeService;

	@GetMapping("/add")
	public String addUserGet(Model model) throws Exception {
		model.addAttribute("user", new User());
		model.addAttribute("userType", userTypeService.selectAllUserType());
		return "addUser";
	}

//	@PostMapping("/add")
//	public String addUserPost(@Valid User user, Errors errors, Model model, HttpSession session) throws Exception {
//		if (errors.hasErrors()) {
//			return "addUser";
//		}
//		if (!userService.isCorrectIdAndPassword(user.getLoginId(), user.getLoginPass())) {
//			errors.rejectValue("statusMessage", "error.incorrect_id_password");
//			return "addUser";
//		}
//		session.setAttribute("loginId", user.getLoginId());
//		return "redirect:rental";// TODO 遷移先
//	}

}
