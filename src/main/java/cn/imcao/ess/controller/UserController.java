package cn.imcao.ess.controller;

import cn.imcao.ess.entity.response.FailResponse;
import cn.imcao.ess.entity.response.Response;
import cn.imcao.ess.entity.response.SuccessResponse;
import cn.imcao.ess.entity.user.UserDO;
import cn.imcao.ess.entity.user.UserInfoVO;
import cn.imcao.ess.entity.user.UserLoginVO;
import cn.imcao.ess.service.UserService;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author ImCaO
 * @description 用户登录登出
 * @date Created at 2021/11/16 16:25
 */

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录请求
     *
     * @param userLoginVO 账号密码
     * @return 登录成功返回 Token
     */
    @PostMapping("/user/login")
    public Response login(@RequestBody UserLoginVO userLoginVO) {

        UserDO userDO = userService.verify(userLoginVO);
        if (userDO != null) {
            String token = JwtUtil.sign(userDO.getUsername(), userDO.getEnterpriseId());
            HashMap<String, String> data = new HashMap<>();
            data.put("token", token);
            return new SuccessResponse(data);
        } else {
            return new FailResponse(500, "用户名和密码不匹配");
        }
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/user/info")
    public Response info() {

        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar("https://www.imcao.cn/avatar.png");
        userInfoVO.setIntroduction("测试用户");
        userInfoVO.setName("测试用户");
        List<String> roles = Collections.singletonList("admin");
        userInfoVO.setRoles(roles);
        return new SuccessResponse(userInfoVO);
    }

    /**
     * 退出登录
     *
     * @param token Token
     * @return 退出成功返回用户名
     */
    @PostMapping("/user/logout")
    public Response logout(@RequestHeader("X-Token") String token) {

        String tokenValue = JwtUtil.verity(token);
        String username = tokenValue.replaceFirst(JwtUtil.TOKEN_SUCCESS, "");
        return new SuccessResponse(username);
    }

    /**
     * Token 验证失败
     *
     * @return 失败响应模型
     */
    @RequestMapping("/user/fail")
    public Response fail() {
        return new FailResponse(50008, "权限验证失败，请重新登录！");
    }
}
