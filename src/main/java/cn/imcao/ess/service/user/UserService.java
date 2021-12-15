package cn.imcao.ess.service.user;

import cn.imcao.ess.entity.user.UserDO;
import cn.imcao.ess.entity.user.UserLoginVO;

/**
 * @author ImCaO
 * @description 用户服务接口
 * @date Created at 2021/11/17 16:12
 */
public interface UserService {

    /**
     * 查询用户信息
     * @param username 用户名
     * @return 用户实体
     */
    UserDO queryByUsername(String username);

    /**
     * 登录验证
     * @param userLoginVO 用户登录 VO
     * @return 成功返回用户实体 失败返回 null
     */
    UserDO verify(UserLoginVO userLoginVO);
}
