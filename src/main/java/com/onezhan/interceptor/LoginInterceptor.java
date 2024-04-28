package com.onezhan.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.onezhan.util.JwtUtils;
import com.onezhan.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, Object handler)throws Exception {
        String token = request.getHeader("Authorization");
        if(JwtUtils.verify(token)) {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if(!JwtUtils.verify(redisToken)) {
                response.setStatus(401);
                return false;
            }
            Map<String, Claim> claims = JwtUtils.getClaims(token);
            ThreadLocalUtil.set(claims);
            return true;
        }
        else {
            response.setStatus(401);
            return false;
        }
    }
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
