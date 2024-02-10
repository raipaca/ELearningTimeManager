package com.raipaca.app.service;

import java.util.List;

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

	@Override
	public List<LearningHour> getLearningList(int userId) throws Exception {
		return lrngHourDao.selectLearningList(userId);
	}

	@Override
	public void deleteLearningData(int listId) throws Exception {
		lrngHourDao.deleteLearningData(listId);
	}

	@Override
	public void setEndDateTime(LearningHour lrngHour) throws Exception {
		lrngHourDao.updateEndDateTime(lrngHour);
	}

	@Override
	public int getTotalPagesByUserId(int userId, int numPerPage) throws Exception {
		double totalNum = (double) lrngHourDao.countByUserId(userId);
		return (int) Math.ceil(totalNum / numPerPage);
	}

	@Override
	public List<LearningHour> getLearningListPageByUserId(int userId, int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return lrngHourDao.selectLimitedByUserId(userId, offset, numPerPage);
	}

}
