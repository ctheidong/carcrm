package com.ruoyi.system.module.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author hlf
 * 2020/5/27 17:24
 * 文件说明：
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    ISysUserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(Authorize.class)) {
            Authorize authorize = method.getAnnotation(Authorize.class);
            if (authorize.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 companyRegistered id
//                Integer userId;
                String  userName;
                String password;
                try {
                    userName = JWT.decode(token).getClaim("username").asString();
//                    password = JWT.decode(token).getClaim("password").asString();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("访问异常！");
                }
                SysUser user = userService.selectUserByLoginName(userName);
                if (StringUtils.isNull(user)) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                Boolean verify = jwtUtil.isVerify(token, user);

                if (!verify) {
                    throw new RuntimeException("非法访问！");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception e) throws Exception {
    }

}
