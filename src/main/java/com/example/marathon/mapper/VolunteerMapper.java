package com.example.marathon.mapper;

import com.example.marathon.dao.Volunteer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VolunteerMapper {

    @Insert("insert into volunteer(Name, CityId, DateOfBirth, Gender) values(#{name}, #{cityId}, #{dateOfBirth}, #{gender})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Volunteer volunteer);

    @Select("""
            <script>
            select count(*)
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
            </script>
            """)
    long count(@Param("cityId") Integer cityId,
            @Param("gender") String gender,
            @Param("keyword") String keyword);

    @Select("""
            <script>
            select Id as id, Name as name, CityId as cityId, DateOfBirth as dateOfBirth, upper(Gender) as gender
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
            limit #{offset}, #{limit}
            </script>
            """)
    List<Volunteer> query(@Param("cityId") Integer cityId,
            @Param("gender") String gender,
            @Param("keyword") String keyword,
            @Param("offset") long offset,
            @Param("limit") int limit);

    @Insert("""
            <script>
            insert into volunteer(Name, CityId, DateOfBirth, Gender)
            values
            <foreach collection="volunteers" item="item" separator=",">
                (#{item.name}, #{item.cityId}, #{item.dateOfBirth}, #{item.gender})
            </foreach>
            </script>
            """)
    int insertBatch(@Param("volunteers") List<Volunteer> volunteers);
}
