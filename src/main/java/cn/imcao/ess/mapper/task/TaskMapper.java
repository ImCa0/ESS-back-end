package cn.imcao.ess.mapper.task;

import cn.imcao.ess.entity.task.TaskDO;
import cn.imcao.ess.entity.task.TaskRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ImCaO
 * @description 制造任务映射
 * @date Created at 2021/12/15 19:10
 */
@Mapper
@Repository
public interface TaskMapper {

    List<TaskDO> queryToBeAcceptedList(@Param("eId") int eId, @Param("taskRequest") TaskRequestVO taskRequest);

    int queryToBeAcceptedTotal(@Param("eId") int eId);

    int accept(@Param("task") TaskDO task);

    int decline(@Param("task") TaskDO task);

    List<TaskDO> queryProcessingList(@Param("eId") int eId, @Param("taskRequest") TaskRequestVO taskRequest);

    int queryProcessingTotal(@Param("eId") int eId);

    List<TaskDO> queryCompletedList(@Param("eId") int eId, @Param("taskRequest") TaskRequestVO taskRequest);

    int queryCompletedTotal(@Param("eId") int eId);

    List<TaskDO> query6Tasks(@Param("eId") int eId);
}
