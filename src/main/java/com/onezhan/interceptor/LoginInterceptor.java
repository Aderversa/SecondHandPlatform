package com.onezhan.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.onezhan.util.JwtUtils;
import com.onezhan.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
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
            Map<String, Claim> claims = JwtUtils.getClaims(token);
            String username = claims.get("username").asString();
            ThreadLocalUtil.set(claims);
            SetOperations<String, String> operations = stringRedisTemplate.opsForSet();
            if(Boolean.FALSE.equals(operations.isMember(username, token))) {
                response.setStatus(401);
                return false;
            }
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
