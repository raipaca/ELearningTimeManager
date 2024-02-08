package com.raipaca.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.raipaca.app.domain.User;

@Mapper
public interface UserDao {

	User selectByLoginId(String loginId) throws Exception;

	User selectById(Integer id) throws Exception;

	void insertUser(User user) throws Exception;

	void updateUser(User user) throws Exception;

	void deleteUser(User user) throws Exception;

}
