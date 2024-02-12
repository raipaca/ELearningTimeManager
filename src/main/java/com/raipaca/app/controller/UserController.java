package com.raipaca.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raipaca.app.domain.User;
import com.raipaca.app.domain.UserForm;
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
		if (user.getName().equals("")) {
			errors.rejectValue("name", "error.user_name_blank");
		}
		if (errors.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("userType", userTypeService.getAllUserType());
			return "addUser";
		}
		if (userService.getUserByLoginId(user.getLoginId()) != null) {
			model.addAttribute("user", user);
			model.addAttribute("userType", userTypeService.getAllUserType());
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

	@GetMapping("/edit")
	public String editUserGet(Model model, HttpSession session) throws Exception {
		User user = userService.getUserById((Integer) session.getAttribute("id"));
		UserForm userForm = new UserForm();
		userForm.setId(user.getId());
		userForm.setName(user.getName());
		userForm.setLoginId(user.getLoginId());
		userForm.setTypeId(user.getTypeId());
		userForm.setCreated(user.getCreated());
		userForm.setUpdated(user.getUpdated());
		userForm.setStatus(user.getStatus());
		model.addAttribute("userName", user.getName());
		model.addAttribute("userForm", userForm);
		model.addAttribute("userType", userTypeService.getUserType(user.getTypeId()));
		return "editUser";
	}

	@PostMapping("/edit")
	public String editUserPost(@Valid UserForm userForm, Errors errors, Model model, HttpSession session) throws Exception {
		User user = userService.getUserById((Integer) session.getAttribute("id"));
		if (userForm.getName().equals("")) {
			errors.rejectValue("name", "error.user_name_blank");
		}
		if (userForm.getChangeLoginPass().equals("") && !userForm.getCheckLoginPass().equals("")) {
			errors.rejectValue("changeLoginPass", "error.changeLoginPass_blank");
		}
		if (userForm.getCheckLoginPass().equals("") && !userForm.getChangeLoginPass().equals("")) {
			errors.rejectValue("changeLoginPass", "error.checkLoginPass_blank");
		}
		if (!userForm.getCheckLoginPass().equals(userForm.getChangeLoginPass())) {
			errors.reject("error.different_loginPass");
		}
		if (errors.hasErrors()) {
			model.addAttribute("userName", user.getName());
			model.addAttribute("userForm", userForm);
			model.addAttribute("userType", userTypeService.getAllUserType());
			return "editUser";
		}
		userForm.setId(user.getId());
		if (userForm.getChangeLoginPass().equals(userForm.getCheckLoginPass())) {
			userForm.setLoginPass(userForm.getChangeLoginPass());
		}
		session.setAttribute("userForm", userForm);
		session.setAttribute("loginPass", userForm.getLoginPass());
		userService.editUser(userForm);
		return "redirect:edit/done";
	}

	@GetMapping("/edit/done")
	public String editUserDoneGet(Model model, HttpSession session) throws Exception {
		UserForm userForm = (UserForm) session.getAttribute("userForm");
		userForm.setLoginPass((String) session.getAttribute("loginPass"));
		model.addAttribute("userName", userForm.getName());
		model.addAttribute("userForm", userForm);
		model.addAttribute("userType", userTypeService.getUserType(userForm.getTypeId()));
		session.removeAttribute("userForm");
		session.removeAttribute("loginPass");
		return "editUserDone";
	}

}
