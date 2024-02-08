package com.raipaca.app.domain;

import java.sql.Time;

import lombok.Data;

@Data
public class LearningHours {

	private Integer id;

	private String startDate;

	private Time startTime;

	private String endDate;

	private Time endTime;

	private Time learntingTime;

}
