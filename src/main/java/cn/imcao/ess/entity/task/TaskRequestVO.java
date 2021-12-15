package cn.imcao.ess.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ImCaO
 * @description 制造任务查询请求 VO
 * @date Created at 2021/12/15 19:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestVO {

    private int page;
    private int limit;
    private String sort;
}
