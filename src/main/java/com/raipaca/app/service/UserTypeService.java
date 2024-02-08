package com.raipaca.app.service;

import java.util.List;

import com.raipaca.app.domain.UserType;

public interface UserTypeService {

	List<UserType> getAllUserType() throws Exception;

	UserType getUserType(int typeId) throws Exception;

}
