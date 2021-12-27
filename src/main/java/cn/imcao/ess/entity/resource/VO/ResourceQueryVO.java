package cn.imcao.ess.entity.resource.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 制造资源实例查询 VO
 * @date Created at 2021/12/27 10:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceQueryVO {

    private int page;
    private int limit;
    private String sort;
    private String identifier;
    private String name;
    private String type;
    private Boolean isShared;
}
