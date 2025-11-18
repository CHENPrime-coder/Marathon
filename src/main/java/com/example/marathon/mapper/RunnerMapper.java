package com.example.marathon.mapper;

import com.example.marathon.table.Runner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RunnerMapper {

    @Select("select Email as email, Name as name, Gender as gender, DateOfBirth as dateOfBirth, CityId as cityId, Experience as experience, Photo as photo from runner where Email=#{email}")
    Runner findByEmail(String email);

    @Insert("insert into runner(Email, Name, Gender, DateOfBirth, CityId, Experience, Photo) values(#{email}, #{name}, #{gender}, #{dateOfBirth}, #{cityId}, #{experience}, #{photo})")
    int insert(Runner runner);

    @Update("update runner set Name=#{name}, Gender=#{gender}, DateOfBirth=#{dateOfBirth}, CityId=#{cityId}, Experience=#{experience}, Photo=#{photo} where Email=#{email}")
    int update(Runner runner);

    @Select("""
            <script>
            select Email as email, Name as name, upper(Gender) as gender, DateOfBirth as dateOfBirth, CityId as cityId, Experience as experience, Photo as photo
            from runner
            <where>
                <if test="cityId!=null">CityId=#{cityId}</if>
                <if test="gender!=null and gender!=''">
                    <if test="cityId!=null">and</if>
                    Gender=#{gender}
                </if>
                <if test="keyword!=null and keyword!=''">
                    <if test="cityId!=null or (gender!=null and gender!='')">and</if>
                    (Name like concat('%', #{keyword}, '%') or Email like concat('%', #{keyword}, '%'))
                </if>
            </where>
            order by Email
            </script>
            """)
    List<Runner> query(@Param("cityId") Integer cityId,
                       @Param("gender") String gender,
                       @Param("keyword") String keyword);
}
