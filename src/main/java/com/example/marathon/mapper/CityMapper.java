package com.example.marathon.mapper;

import com.example.marathon.table.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {
    @Select("select CityId as cityId, CityName as cityName from city order by CityId")
    List<City> findAll();

    @Select("select CityId from city where lower(CityName)=lower(#{name}) limit 1")
    Integer findIdByName(String name);
}
