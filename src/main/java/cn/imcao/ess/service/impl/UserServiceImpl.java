package cn.imcao.ess.service.impl;

import cn.imcao.ess.entity.user.UserDO;
import cn.imcao.ess.entity.user.UserLoginVO;
import cn.imcao.ess.mapper.UserMapper;
import cn.imcao.ess.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ImCaO
 * @description 用户服务实现
 * @date Created at 2021/11/17 16:17
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDO queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    public UserDO verify(UserLoginVO userLoginVO) {
        UserDO userDO = userMapper.queryByUsername(userLoginVO.getUsername());
        if (userDO != null) {
            if (!userDO.getPassword().equals(userLoginVO.getPassword())) {
                userDO = null;
            }
        }
        return userDO;
    }
}
