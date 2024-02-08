package com.raipaca.app.controller;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raipaca.app.domain.LearningHour;
import com.raipaca.app.domain.User;
import com.raipaca.app.service.LearningHourService;
import com.raipaca.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/learning")
public class LearningController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LearningHourService lrngHourService;

	@GetMapping("/start")
	public String startLearningGet(Model model, HttpSession session) throws Exception {
		User user = userService.getUserById((Integer) session.getAttribute("id"));
		model.addAttribute("userName", user.getName());
		LearningHour lrngHour = new LearningHour();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		lrngHour.setDateTimeForDisplay(LocalDateTime.now().format(dtf));
		model.addAttribute("learningHours", lrngHour);
		return "startLearning";
	}

	@PostMapping("/start")
	public String startLearningPost(@Valid LearningHour lrngHour, Errors errors, Model model, HttpSession session) throws Exception {
		// TODO 修了時刻が入力されていないのがないか確認
		int userId = (Integer) session.getAttribute("id");
		if (Objects.isNull(lrngHourService.getDoNotEnd(userId, lrngHour.getStartDate()))) {
			model.addAttribute("learningHours", lrngHour);
			User user = userService.getUserById((Integer) session.getAttribute("id"));
			model.addAttribute("userName", user.getName());
			errors.reject("error.end_date_blank"); // TODO グローバルエラーが表示されない？
			return "startLearning";
		}
		// 形式を整えてDBに登録
		lrngHour.setUserId(userId);
		lrngHour.setDateTimeForDisplay(lrngHour.getDateTimeForDisplay().replaceAll("[-T]", ""));
		lrngHour.setStartDate(lrngHour.getDateTimeForDisplay().substring(0, 8));
		lrngHour.setStartTime(Time.valueOf(lrngHour.getDateTimeForDisplay().substring(8, 13) + ":00"));
		lrngHourService.setStartDateTime(lrngHour);
		return "redirect:list";
	}

}
