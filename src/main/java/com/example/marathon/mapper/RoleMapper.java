package com.example.marathon.mapper;

import com.example.marathon.dao.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("select RoleId as roleId, RoleName as roleName from role where RoleId=#{id}")
    Role findById(Integer id);
}
