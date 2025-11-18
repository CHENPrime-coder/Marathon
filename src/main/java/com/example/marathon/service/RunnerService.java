package com.example.marathon.service;

import com.example.marathon.dto.runner.RunnerCreateRequest;
import com.example.marathon.dto.runner.RunnerUpdateRequest;
import com.example.marathon.exception.BizException;
import com.example.marathon.mapper.RunnerMapper;
import com.example.marathon.mapper.UserMapper;
import com.example.marathon.pojo.Gender;
import com.example.marathon.table.Runner;
import com.example.marathon.table.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RunnerService {
    private static final int RUNNER_ROLE_ID = 2;

    private final RunnerMapper runnerMapper;
    private final UserMapper userMapper;
    private final FileStorageService fileStorageService;

    public RunnerService(RunnerMapper runnerMapper, UserMapper userMapper, FileStorageService fileStorageService) {
        this.runnerMapper = runnerMapper;
        this.userMapper = userMapper;
        this.fileStorageService = fileStorageService;
    }

    public Runner getByEmail(String email) {
        return runnerMapper.findByEmail(email);
    }

    public List<Runner> query(Integer cityId, String gender, String keyword) {
        return runnerMapper.query(cityId, gender, keyword);
    }

    @Transactional
    public void createRunner(RunnerCreateRequest request) {
        if (userMapper.findByEmail(request.getEmail()) != null) {
            throw new BizException(400, "user already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRoleId(RUNNER_ROLE_ID);
        userMapper.insert(user);

        Runner runner = new Runner();
        runner.setEmail(request.getEmail());
        runner.setName(request.getName());
        runner.setGender(Gender.valueOf(request.getGender()));
        runner.setDateOfBirth(request.getDateOfBirth());
        runner.setCityId(request.getCityId());
        runner.setExperience(request.getExperience());
        runner.setPhoto(null);
        runnerMapper.insert(runner);
    }

    @Transactional
    public void updateRunner(String email, RunnerUpdateRequest request) {
        Runner runner = runnerMapper.findByEmail(email);
        if (runner == null) {
            throw new BizException(404, "runner not found");
        }
        runner.setName(request.getName());
        runner.setGender(Gender.valueOf(request.getGender()));
        runner.setDateOfBirth(request.getDateOfBirth());
        runner.setCityId(request.getCityId());
        runner.setExperience(request.getExperience());
        runnerMapper.update(runner);
    }

    @Transactional
    public String updateAvatar(String email, MultipartFile file) {
        Runner runner = runnerMapper.findByEmail(email);
        if (runner == null) {
            throw new BizException(404, "runner not found");
        }
        String url = fileStorageService.saveAvatar(email, file);
        runner.setPhoto(url);
        runnerMapper.update(runner);
        return url;
    }
}
