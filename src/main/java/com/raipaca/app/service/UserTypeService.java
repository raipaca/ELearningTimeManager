package com.raipaca.app.service;

import java.util.List;

import com.raipaca.app.domain.UserType;

public interface UserTypeService {

	List<UserType> selectAllUserType() throws Exception;

}
