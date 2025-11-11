package com.example.marathon.service;

import com.example.marathon.mapper.UserMapper;
import com.example.marathon.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean loginWithEmail(String email, String password) {
        User user = userMapper.getUsersByEmail(email);
        if (user == null){ return false;}
        return user.getPassword().equals(password);
    }
}
