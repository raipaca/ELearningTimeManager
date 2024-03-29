package com.raipaca.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.raipaca.app.domain.LearningHour;

@Mapper
public interface LearningHourDao {

	LearningHour selectDoNotEnd(@Param("userId") int userId, @Param("startDate") String startDate) throws Exception;

	void insertStartDateTime(LearningHour lrngHour) throws Exception;

	List<LearningHour> selectLearningList(int userId) throws Exception;

	void deleteLearningData(int listId) throws Exception;

	void updateEndDateTime(LearningHour lrngHour) throws Exception;

	// ページ分割機能用
	Long countByUserId(int userId) throws Exception;

	// ページ分割機能用
	List<LearningHour> selectLimitedByUserId(@Param("userId") int userId, @Param("offset") int offset,
			@Param("num") int num) throws Exception;

	LearningHour selectLearningHourById(int id) throws Exception;

}
