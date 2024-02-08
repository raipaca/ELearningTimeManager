package com.raipaca.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raipaca.app.dao.LearningHourDao;
import com.raipaca.app.domain.LearningHour;

@Service
public class LearningHourServiceImpl implements LearningHourService {

	@Autowired
	LearningHourDao lrngHourDao;

	@Override
	public LearningHour getDoNotEnd(int userId, String startDate) throws Exception {
		return lrngHourDao.selectDoNotEnd(userId, startDate);
	}

	@Override
	public void setStartDateTime(LearningHour lrngHour) throws Exception {
		lrngHourDao.insertStartDateTime(lrngHour);
	}

}
