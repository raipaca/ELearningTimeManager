package com.raipaca.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.raipaca.app.domain.LearningHour;

@Mapper
public interface LearningHourDao {

	LearningHour selectDoNotEnd(@Param("userId") int userId, @Param("startDate") String startDate) throws Exception;

	void insertStartDateTime(LearningHour lrngHour) throws Exception;

}
