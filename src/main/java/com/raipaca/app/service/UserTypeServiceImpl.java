package com.raipaca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raipaca.app.dao.UserTypeDao;
import com.raipaca.app.domain.UserType;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	UserTypeDao userTypeDao;

	@Override
	public List<UserType> selectAllUserType() throws Exception {
		return userTypeDao.selectAllUserType();
	}

}
