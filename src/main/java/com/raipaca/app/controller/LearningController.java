package com.raipaca.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raipaca.app.domain.LearningHours;

@Controller
@RequestMapping("/learning")
public class LearningController {

	@GetMapping("/start")
	public String startLearningGet(Model model) throws Exception {
		model.addAttribute("learningHours", new LearningHours());
		return "startLearning";
	}

}
