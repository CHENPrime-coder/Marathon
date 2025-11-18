package com.example.marathon.mapper;

import com.example.marathon.table.Volunteer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VolunteerMapper {

    @Insert("insert into volunteer(Name, CityId, DateOfBirth, Gender) values(#{name}, #{cityId}, #{dateOfBirth}, #{gender})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Volunteer volunteer);

    @Select("""
            <script>
            select Id as id, Name as name, CityId as cityId, DateOfBirth as dateOfBirth, Gender as gender
            from volunteer
            <where>
                <if test="cityId!=null">CityId=#{cityId}</if>
                <if test="gender!=null and gender!=''">
                    <if test="cityId!=null">and</if>
                    Gender=#{gender}
                </if>
                <if test="keyword!=null and keyword!=''">
                    <if test="cityId!=null or (gender!=null and gender!='')">and</if>
                    Name like concat('%', #{keyword}, '%')
                </if>
            </where>
            order by Id
            </script>
            """)
    List<Volunteer> query(@Param("cityId") Integer cityId,
                          @Param("gender") String gender,
                          @Param("keyword") String keyword);
}
