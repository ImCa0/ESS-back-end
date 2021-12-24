package cn.imcao.ess.entity.resource.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 制造资源类型查询 VO
 * @date Created at 2021/12/23 10:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTypeQueryVO {

    private int page;
    private int limit;
    private String sort;
    private String name;
    private String tag;
    private String type;
}
