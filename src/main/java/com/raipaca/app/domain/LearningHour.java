package com.raipaca.app.domain;

import java.sql.Time;

import lombok.Data;

@Data
public class LearningHour {

	private Integer id;

	private Integer userId;

	private String dateTimeForDisplay;

	private String startDate;

	private Time startTime;

	private String endDate;

	private Time endTime;

	private Time learningTime;

}
