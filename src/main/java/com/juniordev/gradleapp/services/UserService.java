package com.juniordev.gradleapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.juniordev.gradleapp.beans.User;
import com.juniordev.gradleapp.mappers.UserMapper;

@Component
public class UserService implements UserDetailsService {

  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.selectByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("ユーザーが存在しない");
    }
    return user;
  }
  
}
