package cn.imcao.ess.config;

import cn.imcao.ess.entity.user.TokenVerity;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ImCaO
 * @description Token 验证拦截器
 * @date Created at 2021/11/16 16:43
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String token = request.getHeader("X-Token");
            if (token != null) {
                TokenVerity tokenVerity = JwtUtil.verity(token);
                if (tokenVerity.isSuccess()) {
                    return true;
                }
            }
            response.sendRedirect(request.getContextPath() + "/user/fail");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
