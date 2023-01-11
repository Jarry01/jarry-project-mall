package com.tencent.mall.interceptor;

import com.tencent.mall.util.JwtUtils;
import com.tencent.mall.util.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 *
 * @author Jarry
 */
public class SysInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        if (handler instanceof HandlerMethod) {
            String token = request.getHeader("token");
            if (StringUtil.isEmpty(token)) {
                throw new RuntimeException("签名验证不存在");
            } else {
                Claims claims = JwtUtils.validateJWT(token).getClaims();
                // 管理员 admin开头
                if (path.startsWith("/admin")) {
                    if (claims == null || !claims.getSubject().equals("admin") || !claims.getId().equals("-1")) {
                        throw new RuntimeException("鉴权失败！");
                    } else {
                        return true;
                    }
                } else {
                    // 普通用户 鉴权
                    if (claims == null) {
                        throw new RuntimeException("鉴权失败！");
                    } else {
                        return true;
                    }
                }
            }
        } else {
            return true;
        }
    }


}
