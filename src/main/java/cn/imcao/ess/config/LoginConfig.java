package cn.imcao.ess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ImCaO
 * @description 登录拦截器
 * @date Created at 2021/11/16 16:53
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new TokenInterceptor());
        registration.addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/fail", "/**/*.html", "/**/*.js", "/**/*.css", "/**/*.woff");
    }
}
