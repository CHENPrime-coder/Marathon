package com.example.marathon.mapper;

import com.example.marathon.table.RaceKitOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RaceKitOptionMapper {
    @Select("select OptionId as optionId, `Option` as `option`, Cost as cost from racekitoption order by OptionId")
    List<RaceKitOption> findAll();

    @Select("select OptionId as optionId, `Option` as `option`, Cost as cost from racekitoption where OptionId=#{id}")
    RaceKitOption findById(Integer id);
}
