package com.example.marathon.service;

import com.example.marathon.dto.auth.LoginRequest;
import com.example.marathon.dto.auth.LoginResponse;
import com.example.marathon.dto.auth.UpdatePasswordRequest;
import com.example.marathon.exception.BizException;
import com.example.marathon.mapper.RoleMapper;
import com.example.marathon.mapper.UserMapper;
import com.example.marathon.security.TokenService;
import com.example.marathon.dao.Role;
import com.example.marathon.dao.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final TokenService tokenService;
    private final CaptchaService captchaService;

    public AuthService(UserMapper userMapper, RoleMapper roleMapper, TokenService tokenService, CaptchaService captchaService) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.tokenService = tokenService;
        this.captchaService = captchaService;
    }

    public LoginResponse login(LoginRequest request) {
        if (!captchaService.validate(request.getCaptchaId(), request.getCaptchaCode())) {
            throw new BizException(400, "captcha invalid");
        }
        User user = userMapper.findByEmail(request.getEmail());
        if (user == null) {
            throw new BizException(400, "user not found");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BizException(400, "password incorrect");
        }
        Role role = roleMapper.findById(user.getRoleId());
        String token = tokenService.generateToken(user.getEmail());
        return new LoginResponse(token, user.getEmail(),
                role == null ? null : role.getRoleId(),
                role == null ? null : role.getRoleName());
    }

    @Transactional
    public void updatePassword(String email, UpdatePasswordRequest request) {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new BizException(400, "user not found");
        }
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BizException(400, "old password incorrect");
        }
        userMapper.updatePassword(email, request.getNewPassword());
    }
}
