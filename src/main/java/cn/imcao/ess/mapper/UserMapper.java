package cn.imcao.ess.mapper;

import cn.imcao.ess.entity.user.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ImCaO
 * @description 用户映射
 * @date Created at 2021/11/17 16:04
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 用户名查询
     * @param username 用户名
     * @return 用户实体
     */
    UserDO queryByUsername(@Param("username") String username);
}
