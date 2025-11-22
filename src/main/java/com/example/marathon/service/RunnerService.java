package com.example.marathon.service;

import com.example.marathon.api.PageResponse;
import com.example.marathon.dao.City;
import com.example.marathon.dto.runner.RunnerCreateRequest;
import com.example.marathon.dto.runner.RunnerDetails;
import com.example.marathon.dto.runner.RunnerResponse;
import com.example.marathon.dto.runner.RunnerUpdateRequest;
import com.example.marathon.exception.BizException;
import com.example.marathon.mapper.CityMapper;
import com.example.marathon.mapper.RunnerMapper;
import com.example.marathon.mapper.UserMapper;
import com.example.marathon.pojo.Gender;
import com.example.marathon.dao.Runner;
import com.example.marathon.dao.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RunnerService {
    private static final int RUNNER_ROLE_ID = 2;

    private final RunnerMapper runnerMapper;
    private final UserMapper userMapper;
    private final CityMapper cityMapper;

    public RunnerService(RunnerMapper runnerMapper, UserMapper userMapper, CityMapper cityMapper) {
        this.runnerMapper = runnerMapper;
        this.userMapper = userMapper;
        this.cityMapper = cityMapper;
    }

    public Runner getByEmail(String email) {
        return runnerMapper.findByEmail(email);
    }

    public RunnerDetails getDetailsByEmail(String email) {
        Runner runner = runnerMapper.findByEmail(email);
        if (runner == null) {
            throw new BizException(404, "跑步者未找到");
        }
        City city = cityMapper.findById(runner.getCityId());
        if (city == null) {
            throw new BizException(404, "城市未找到");
        }
        RunnerDetails runnerDetails = new RunnerDetails();
        BeanUtils.copyProperties(runner, runnerDetails);
        runnerDetails.setCityName(city.getCityName());
        return runnerDetails;
    }

    public PageResponse<RunnerResponse> query(Integer cityId, String gender, String keyword,
                                              int page, int size) {
        if (page < 1) {
            page = 1;
        }
        long offset = (long) (page - 1) * size;
        long total = runnerMapper.count(cityId, gender, keyword);
        List<RunnerResponse> list = runnerMapper.query(cityId, gender, keyword, offset, size);
        return new PageResponse<>(total, list);
    }

    @Transactional
    public void createRunner(RunnerCreateRequest request) {
        if (userMapper.findByEmail(request.getEmail()) != null) {
            throw new BizException(400, "用户已存在");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRoleId(RUNNER_ROLE_ID);
        userMapper.insert(user);

        Runner runner = new Runner();
        runner.setEmail(request.getEmail());
        runner.setName(request.getName());
        runner.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        runner.setDateOfBirth(request.getDateOfBirth());
        runner.setCityId(request.getCityId());
        runner.setExperience(request.getExperience());
        runner.setPhoto(request.getPhoto());
        runnerMapper.insert(runner);
    }

    @Transactional
    public void updateRunner(String email, RunnerUpdateRequest request) {
        Runner runner = runnerMapper.findByEmail(email);
        if (runner == null) {
            throw new BizException(404, "跑步者未找到");
        }
        BeanUtils.copyProperties(request, runner);
        runnerMapper.update(runner);
    }
}
