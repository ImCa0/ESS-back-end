package cn.imcao.ess.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 用户实体 DO
 * @date Created at 2021/11/17 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {

    private int id;
    private String username;
    private String password;
    private int enterpriseId;
    private String name;
    private String roles;
    private String mobile;
    private String email;
    private String avatar;
    private String introduction;
}
