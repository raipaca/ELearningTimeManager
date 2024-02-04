package com.raipaca.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.raipaca.app.domain.UserType;

@Mapper
public interface UserTypeDao {

	List<UserType> selectAllUserType() throws Exception;

}
