package com.example.marathon.mapper;

import com.example.marathon.table.Registration;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegistrationMapper {

    @Select("select Email as email, OptionId as optionId, CompetitionId as competitionId, TotalPrice as totalPrice from registration where Email=#{email} and CompetitionId=#{competitionId}")
    Registration findOne(@Param("email") String email, @Param("competitionId") Integer competitionId);

    @Insert("insert into registration(Email, OptionId, CompetitionId, TotalPrice) values(#{email}, #{optionId}, #{competitionId}, #{totalPrice})")
    int insert(Registration registration);

    @Update("update registration set OptionId=#{optionId}, TotalPrice=#{totalPrice} where Email=#{email} and CompetitionId=#{competitionId}")
    int update(Registration registration);

    @Select("""
            <script>
            select Email as email, OptionId as optionId, CompetitionId as competitionId, TotalPrice as totalPrice
            from registration
            <where>
            <if test="email!=null">Email=#{email}</if>
            <if test="competitionId!=null">
                <if test="email!=null">and</if>
                CompetitionId=#{competitionId}
            </if>
            </where>
            </script>
            """)
    List<Registration> query(@Param("email") String email, @Param("competitionId") Integer competitionId);
}
