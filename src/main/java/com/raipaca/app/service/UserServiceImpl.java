package com.raipaca.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raipaca.app.dao.UserDao;
import com.raipaca.app.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public boolean isCorrectLoginIdAndPassword(String loginId, String loginPass) throws Exception {
		User user = userDao.selectByLoginId(loginId);
		if (user == null) {
			return false;
		}
		if (!BCrypt.checkpw(loginPass, user.getLoginPass())) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByLoginId(String loginId) throws Exception {
		User user = userDao.selectByLoginId(loginId);
		if (user != null) {
			return user;
		}
		return null;
	}

	@Override
	public User getUserById(Integer id) throws Exception {
		return userDao.selectById(id);
	}

	@Override
	public void addUser(User user) throws Exception {
		user.setLoginPass(BCrypt.hashpw(user.getLoginPass(), BCrypt.gensalt(10)));
		userDao.insertUser(user);
	}

}
