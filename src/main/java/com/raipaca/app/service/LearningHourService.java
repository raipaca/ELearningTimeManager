package com.raipaca.app.service;

import com.raipaca.app.domain.LearningHour;

public interface LearningHourService {

	LearningHour getDoNotEnd(int userId, String startDate) throws Exception;

	void setStartDateTime(LearningHour lrngHour) throws Exception;

}
