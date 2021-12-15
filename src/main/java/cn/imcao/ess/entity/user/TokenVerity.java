package cn.imcao.ess.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description Token 验证结果
 * @date Created at 2021/12/15 10:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVerity {
    private boolean success;
    private String username;
    private String enterpriseId;
}
