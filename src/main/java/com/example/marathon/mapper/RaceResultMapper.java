package com.example.marathon.mapper;

import com.example.marathon.table.RaceResult;
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
                   r.RunnerEmail as runnerEmail
            from raceresult r
            left join runner ru on r.RunnerEmail = ru.Email
            <where>
                <if test="competitionId!=null">r.CompetitionId=#{competitionId}</if>
                <if test="runnerEmail!=null">
                    <if test="competitionId!=null">and</if>
                    r.RunnerEmail=#{runnerEmail}
                </if>
                <if test="status!=null">
                    <if test="competitionId!=null or runnerEmail!=null">and</if>
                    r.Status=#{status}
                </if>
                <if test="runnerName!=null and runnerName!=''">
                    <if test="competitionId!=null or runnerEmail!=null or status!=null">and</if>
                    ru.Name like concat('%', #{runnerName}, '%')
                </if>
            </where>
            </script>
            """)
    List<RaceResult> query(@Param("competitionId") Integer competitionId,
                           @Param("runnerEmail") String runnerEmail,
                           @Param("status") Integer status,
                           @Param("runnerName") String runnerName);
}
