package cn.imcao.ess.controller;

import cn.imcao.ess.entity.resource.DO.HasProperty;
import cn.imcao.ess.entity.resource.DO.Resource;
import cn.imcao.ess.entity.resource.VO.ResourceQueryVO;
import cn.imcao.ess.entity.response.FailResponse;
import cn.imcao.ess.entity.response.Response;
import cn.imcao.ess.entity.response.SuccessResponse;
import cn.imcao.ess.entity.user.TokenVerity;
import cn.imcao.ess.service.resource.ResourceService;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author ImCaO
 * @description 制造资源实例
 * @date Created at 2021/12/27 11:09
 */

@RestController
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public Response queryPage(@RequestHeader("X-Token") String token, ResourceQueryVO vo) {
        TokenVerity verity = JwtUtil.verity(token);
        int enterpriseId = verity.getEnterpriseId();
        try {
            Page<Resource> page = resourceService.queryPage(enterpriseId, vo);
            List<Resource> list = page.getContent();
            long total = page.getTotalElements();
            HashMap<String, Object> res = new HashMap<>();
            res.put("list", list);
            res.put("total", total);
            return new SuccessResponse(res);
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Response queryById(@PathVariable String id) {
        try {
            Resource resource = resourceService.queryById(id);
            return new SuccessResponse(resource);
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @PostMapping
    public Response createResource(@RequestHeader("X-Token") String token,
                                   @RequestBody Resource resource) {
        TokenVerity verity = JwtUtil.verity(token);
        resource.setCreateBy(verity.getUsername());
        try {
            resourceService.createResource(resource);
            return new SuccessResponse("创建成功");
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @PutMapping
    public Response updateResource(@RequestBody Resource resource) {
        try {
            Integer update = resourceService.updateResource(resource);
            if (update == 1) {
                return new SuccessResponse("更新成功");
            } else {
                return new FailResponse(400, "更新失败");
            }
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @DeleteMapping
    public Response deleteResource(@RequestBody Resource resource) {
        try {
            resourceService.deleteResource(resource);
            return new SuccessResponse("删除成功");
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @PutMapping("/property")
    public Response updateProperty(@RequestBody HasProperty hasProperty) {
        try {
            resourceService.updateProperty(hasProperty);
            return new SuccessResponse("更新成功");
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }
}
