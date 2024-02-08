package com.raipaca.app.service;

import java.util.List;

import com.raipaca.app.domain.LearningHour;

public interface LearningHourService {

	LearningHour getDoNotEnd(int userId, String startDate) throws Exception;

	void setStartDateTime(LearningHour lrngHour) throws Exception;

	List<LearningHour> getLearningList(int userId) throws Exception;

	void deleteLearningData(int listId) throws Exception;

	void setEndDateTime(LearningHour lrngHour) throws Exception;

}
