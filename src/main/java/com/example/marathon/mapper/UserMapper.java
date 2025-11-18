package com.example.marathon.mapper;

import com.example.marathon.table.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select Email as email, Password as password, RoleId as roleId from user where Email=#{email}")
    User findByEmail(String email);

    @Insert("insert into user(Email, Password, RoleId) values(#{email}, #{password}, #{roleId})")
    int insert(User user);

    @Update("update user set Password=#{password} where Email=#{email}")
    void updatePassword(@Param("email") String email, @Param("password") String password);
}
