package com.juniordev.gradleapp.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.juniordev.gradleapp.beans.User;

@Mapper
public interface UserMapper {
  public User selectByUsername(String username);
}
