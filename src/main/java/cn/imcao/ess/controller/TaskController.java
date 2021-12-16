package cn.imcao.ess.controller;

import cn.imcao.ess.entity.response.FailResponse;
import cn.imcao.ess.entity.response.Response;
import cn.imcao.ess.entity.response.SuccessResponse;
import cn.imcao.ess.entity.task.TaskDO;
import cn.imcao.ess.entity.task.TaskRequestVO;
import cn.imcao.ess.entity.user.TokenVerity;
import cn.imcao.ess.service.task.TaskService;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ImCaO
 * @description 制造任务
 * @date Created at 2021/12/16 11:03
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/to-be-accepted")
    public Response toBeAccepted(@RequestHeader("X-Token") String token, TaskRequestVO taskRequestVO) {
        TokenVerity verity = JwtUtil.verity(token);
        Map<String, Object> res = taskService.fetchToBeAccepted(verity.getEnterpriseId(), taskRequestVO);
        return new SuccessResponse(res);
    }

    @PostMapping("/accept")
    public Response accept(@RequestBody TaskDO taskDO) {
        int flag = taskService.accept(taskDO);
        if (flag == 1) {
            return new SuccessResponse("接受成功");
        } else {
            return new FailResponse(500,  "接受失败");
        }
    }

    @PostMapping("/decline")
    public Response decline(@RequestBody TaskDO taskDO) {
        int flag = taskService.decline(taskDO);
        if (flag == 1) {
            return new SuccessResponse("拒绝成功");
        } else {
            return new FailResponse(500,  "拒绝失败");
        }
    }

    @GetMapping("/processing")
    public Response processing(@RequestHeader("X-Token") String token, TaskRequestVO taskRequestVO) {
        TokenVerity verity = JwtUtil.verity(token);
        Map<String, Object> res = taskService.fetchProcessing(verity.getEnterpriseId(), taskRequestVO);
        return new SuccessResponse(res);
    }
}
