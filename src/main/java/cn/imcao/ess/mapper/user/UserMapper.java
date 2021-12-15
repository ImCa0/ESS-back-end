package cn.imcao.ess.mapper.user;

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

    UserDO queryByUsername(@Param("username") String username);
}
