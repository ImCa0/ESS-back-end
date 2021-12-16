package cn.imcao.ess.service.task;

import cn.imcao.ess.entity.task.TaskDO;
import cn.imcao.ess.entity.task.TaskRequestVO;

import java.util.List;
import java.util.Map;

/**
 * @author ImCaO
 * @description 制造任务服务接口
 * @date Created at 2021/12/15 19:22
 */
public interface TaskService {

    /**
     * 待接受任务页面请求
     * @param eId 企业 ID
     * @param taskRequest 制造任务请求 VO
     * @return 待接受任务列表、总数
     */
    Map<String, Object> fetchToBeAccepted(int eId, TaskRequestVO taskRequest);

    /**
     * 接受任务
     * @param task 制造任务实体 DO
     * @return 成功返回 1，失败返回 0
     */
    int accept(TaskDO task);

    /**
     * 拒绝任务
     * @param task 制造任务实体 DO
     * @return 成功返回 1，失败返回 0
     */
    int decline(TaskDO task);

    /**
     * 正在加工任务页面请求
     * @param eId 企业 ID
     * @param taskRequest 制造任务请求 VO
     * @return 待接受任务列表、总数
     */
    Map<String, Object> fetchProcessing(int eId, TaskRequestVO taskRequest);

    /**
     * 已完成任务页面请求
     * @param eId 企业 ID
     * @param taskRequest 制造任务请求 VO
     * @return 待接受任务列表、总数
     */
    Map<String, Object> fetchCompleted(int eId, TaskRequestVO taskRequest);

    /**
     * 查询最近 6 个任务，用于首页展示
     * @return 最近 6 个任务
     */
    List<TaskDO> query6Tasks(int eId);
}
