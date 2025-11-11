package com.example.marathon.mapper;

import com.example.marathon.table.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where Email=#{email}")
    User getUsersByEmail(String email);

}
