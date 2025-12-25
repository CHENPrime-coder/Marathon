package com.example.marathon.mapper;

import com.example.marathon.dao.RaceResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RaceResultMapper {

    @Select("""
            <script>
            select r.ResultId as resultId,
                   r.Status as status,
                   r.CompletionTime as completionTime,
                   r.CompetitionId as competitionId,
                   c2.Name as competitionName,
                   r.RunnerEmail as runnerEmail,
                   ru.Gender as runnerGender,
                   ru.Photo as runnerAvatar,
                   c.CityName as runnerCity,
                   ru.Experience as runnerExperienceLevel,
                   ru.Name as runnerName
            from raceresult r
            left join runner ru on r.RunnerEmail = ru.Email
            left join city c on ru.CityId = c.CityId
            left join competition c2 on r.CompetitionId = c2.Id
            <where>
                <if test="competitionId!=null and competitionId!=''">r.CompetitionId=#{competitionId}</if>
                <if test="gender!=null and gender!=''">
                    <if test="competitionId!=null">and</if>
                    ru.Gender=#{gender}
                </if>
            </where>
            </script>
            """)
    List<RaceResult> query(@Param("competitionId") Integer competitionId,
            @Param("gender") String gender);

    @Select("""
            select r.ResultId as resultId,
                   r.Status as status,
                   r.CompletionTime as completionTime,
                   r.CompetitionId as competitionId,
                   c2.Name as competitionName,
                   r.RunnerEmail as runnerEmail,
                   ru.Gender as runnerGender,
                   ru.Photo as runnerAvatar,
                   c.CityName as runnerCity,
                   ru.Experience as runnerExperienceLevel,
                   ru.Name as runnerName
            from raceresult r
            left join runner ru on r.RunnerEmail = ru.Email
            left join city c on ru.CityId = c.CityId
            left join competition c2 on r.CompetitionId = c2.Id
            where r.ResultId = #{resultId}
            """)
    RaceResult findById(@Param("resultId") Integer resultId);
}
