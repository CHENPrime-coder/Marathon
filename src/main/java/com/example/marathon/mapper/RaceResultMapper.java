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
                   r.RunnerEmail as runnerEmail,
                   ru.Photo as runnerAvatar,
                   c.CityName as runnerCity,
                   ru.Experience as runnerExperienceLevel
            from raceresult r
            left join runner ru on r.RunnerEmail = ru.Email
            left join city c on ru.CityId = c.CityId
            <where>
                <if test="competitionId!=null">r.CompetitionId=#{competitionId}</if>
                <if test="gender!=null">
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
                   r.RunnerEmail as runnerEmail,
                   ru.Photo as runnerAvatar,
                   c.CityName as runnerCity,
                   ru.Experience as runnerExperienceLevel
            from raceresult r
            left join runner ru on r.RunnerEmail = ru.Email
            left join city c on ru.CityId = c.CityId
            where r.ResultId = #{resultId}
            """)
    RaceResult findById(@Param("resultId") Integer resultId);
}
