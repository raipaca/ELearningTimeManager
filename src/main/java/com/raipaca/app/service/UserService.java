package com.raipaca.app.service;

import com.raipaca.app.domain.User;

public interface UserService {

	boolean isCorrectLoginIdAndPassword(String loginId, String loginPass) throws Exception;

	User getUserByLoginId(String loginId) throws Exception;
	
	User getUserById(Integer id) throws Exception;

	void addUser(User user) throws Exception;

}
