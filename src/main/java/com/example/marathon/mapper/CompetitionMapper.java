package com.example.marathon.mapper;

import com.example.marathon.dao.Competition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompetitionMapper {
    @Select("select Id as id, Name as name, RegCost as regCost from competition order by Id")
    List<Competition> findAll();

    @Select("select Id as id, Name as name, RegCost as regCost from competition where Id=#{id}")
    Competition findById(Integer id);

    @Insert("insert into competition(Name, RegCost) values(#{name}, #{regCost})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Competition competition);
}
