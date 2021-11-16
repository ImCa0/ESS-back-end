package cn.imcao.ess.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ImCaO
 * @description 用户信息实体类
 * @date Created at 2021/11/16 18:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
}
