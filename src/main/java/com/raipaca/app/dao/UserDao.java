package com.raipaca.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.raipaca.app.domain.User;
import com.raipaca.app.domain.UserForm;

@Mapper
public interface UserDao {

	User selectByLoginId(String loginId) throws Exception;

	User selectById(Integer id) throws Exception;

	void insertUser(User user) throws Exception;

	void deleteUser(User user) throws Exception;

	void updateUser(UserForm userForm) throws Exception;

}
