package cn.imcao.ess.service.task.impl;

import cn.imcao.ess.entity.task.TaskDO;
import cn.imcao.ess.entity.task.TaskRequestVO;
import cn.imcao.ess.mapper.task.TaskMapper;
import cn.imcao.ess.service.task.TaskService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ImCaO
 * @description 制造任务服务实现
 * @date Created at 2021/12/15 19:25
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public Map<String, Object> fetchToBeAccepted(int eId, TaskRequestVO taskRequest) {
        HashMap<String, Object> map = new HashMap<>();
        taskRequest.setPage((taskRequest.getPage() - 1) * taskRequest.getLimit());
        List<TaskDO> tasks = taskMapper.queryToBeAcceptedList(eId, taskRequest);
        int total = taskMapper.queryToBeAcceptedTotal(eId);
        map.put("list", tasks);
        map.put("total", total);
        return map;
    }

    @Override
    public int accept(TaskDO task) {
        return taskMapper.accept(task);
    }

    @Override
    public int decline(TaskDO task) {
        return taskMapper.decline(task);
    }

    @Override
    public List<TaskDO> queryProcessingList(int eId, TaskRequestVO taskRequest) {
        taskRequest.setPage((taskRequest.getPage() - 1) * taskRequest.getLimit());
        return taskMapper.queryProcessingList(eId, taskRequest);
    }

    @Override
    public int queryProcessingTotal(int eId) {
        return taskMapper.queryProcessingTotal(eId);
    }

    @Override
    public List<TaskDO> queryCompletedList(int eId, TaskRequestVO taskRequest) {
        taskRequest.setPage((taskRequest.getPage() - 1) * taskRequest.getLimit());
        return taskMapper.queryCompletedList(eId, taskRequest);
    }

    @Override
    public int queryCompletedTotal(int eId) {
        return taskMapper.queryCompletedTotal(eId);
    }

    @Override
    public List<TaskDO> query6Tasks(int eId) {
        return taskMapper.query6Tasks(eId);
    }
}
