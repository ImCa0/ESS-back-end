package cn.imcao.ess.controller;

import cn.imcao.ess.entity.response.Response;
import cn.imcao.ess.entity.response.SuccessResponse;
import cn.imcao.ess.entity.task.TaskDO;
import cn.imcao.ess.entity.user.TokenVerity;
import cn.imcao.ess.service.task.TaskService;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ImCaO
 * @description 首页
 * @date Created at 2021/12/15 15:03
 */

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final TaskService taskService;

    public DashboardController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/overview")
    public Response overview(@RequestHeader("X-Token") String token) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("profit", 8888);
        map.put("machineCount", 123);
        map.put("taskCount", 321);
        map.put("reputation", 99);
        return new SuccessResponse(map);
    }

    @GetMapping("/profit-task")
    public Response profitAndTask(@RequestHeader("X-Token") String token) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("profit", Arrays.asList(10646, 8787, 9564, 8888, 7777, 6856, 9280, 10231, 14324, 11111));
        map.put("taskCompleted", Arrays.asList(355, 245, 199, 266, 279, 239, 320, 232, 352, 276));
        map.put("taskUncompleted", Arrays.asList(56, 60, 30, 120, 78, 56, 67, 88, 56, 34));
        return new SuccessResponse(map);
    }

    @GetMapping("/usage")
    public Response usage(@RequestHeader("X-Token") String token) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("occupied", 456);
        map.put("free", 123);
        return new SuccessResponse(map);
    }

    @GetMapping("/fault")
    public Response fault(@RequestHeader("X-Token") String token) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("normal", 987);
        map.put("faulty", 321);
        return new SuccessResponse(map);
    }

    @GetMapping("/load-quality")
    public Response loadAndQuality(@RequestHeader("X-Token") String token) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("load", Arrays.asList(0.84, 0.76, 0.96, 0.87, 0.77, 0.78, 0.82, 0.88, 0.75));
        map.put("quality", Arrays.asList(0.99, 0.97, 0.95, 0.96, 0.98, 0.92, 0.97, 0.95, 0.99));
        return new SuccessResponse(map);
    }

    @GetMapping("/delivery-reputation")
    public Response deliveryAndReputation(@RequestHeader("X-Token") String token) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("delivery", Arrays.asList(0.83, 0.92, 0.98, 0.87, 1, 0.73, 0.83, 0.92, 0.96));
        map.put("reputation", Arrays.asList(100, 98, 97, 95, 97, 93, 90, 92, 95));
        return new SuccessResponse(map);
    }

    @GetMapping("/task")
    public Response task(@RequestHeader("X-Token") String token) {
        TokenVerity verity = JwtUtil.verity(token);
        List<TaskDO> tasks = taskService.query6Tasks(verity.getEnterpriseId());
        return new SuccessResponse(tasks);
    }
}
