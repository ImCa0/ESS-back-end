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
import java.util.UUID;

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

    @GetMapping("/query")
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

    @PostMapping("/create/{typeId}")
    public Response createResource(@RequestHeader("X-Token") String token,
                                   @PathVariable("typeId") String uuid,
                                   Resource resource) {
        TokenVerity verity = JwtUtil.verity(token);
        resource.setCreateBy(verity.getUsername());
        try {
            Integer create = resourceService.createResource(UUID.fromString(uuid), resource);
            if (create == 1) {
                return new SuccessResponse("创建成功");
            } else {
                return new FailResponse(400, "创建失败");
            }
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @PostMapping("/update")
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

    @PostMapping("/property")
    public Response updateProperty(@RequestBody HasProperty hasProperty) {
        try {
            resourceService.updateProperty(hasProperty);
            return new SuccessResponse("更新成功");
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }
}
