package com.onezhan.controller;

import com.auth0.jwt.interfaces.Claim;
import com.mysql.cj.util.TimeUtil;
import com.onezhan.pojo.Result;
import com.onezhan.pojo.User;
import com.onezhan.service.UserService;
import com.onezhan.util.JwtUtils;
import com.onezhan.util.MD5Util;
import com.onezhan.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin // 支持跨域，axios可以访问下面的接口哦
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username,
                           @Pattern(regexp = "^\\S{5,16}$")String password,
                           @Pattern(regexp = "^\\S{5,16}$")String confirmpassword) {
        User user = userService.getByUserName(username);
        if(user == null) {
            if(!password.equals(confirmpassword)) {
                return Result.error("两次输入的密码不相同");
            }
            userService.register(username, MD5Util.md5(password));
            return Result.success();
        }
        else {
            return Result.error("用户已存在");
        }
    }
    @PostMapping("/login")
    public Result login(String username, String password) {
         // 先拿到用户的信息
        User user = userService.getByUserName(username);
        if(user == null) {
            return Result.error("用户不存在");
        }
        if(!user.getPassword().equals(MD5Util.md5(password))) {
            return Result.error("密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        String token = JwtUtils.sign(map);
        assert token != null;
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(token, token, JwtUtils.getExpireTime(), TimeUnit.SECONDS);
        return Result.success(token);
    }
    @GetMapping("/getInfo")
    public Result getInfo() {
        Map<String, Claim> claims = ThreadLocalUtil.get();
        String username = claims.get("username").asString();
        User user = userService.getByUserName(username);
        return Result.success(user);
    }
}
