package cn.imcao.ess.controller;

import cn.imcao.ess.entity.resource.DO.ResourceType;
import cn.imcao.ess.entity.resource.VO.ResourceTypeQueryVO;
import cn.imcao.ess.entity.response.FailResponse;
import cn.imcao.ess.entity.response.Response;
import cn.imcao.ess.entity.response.SuccessResponse;
import cn.imcao.ess.entity.user.TokenVerity;
import cn.imcao.ess.service.resource.ResourceTypeService;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author ImCaO
 * @description 制造资源类型
 * @date Created at 2021/12/23 10:54
 */

@RestController
@RequestMapping("/resource/type")
public class ResourceTypeController {

    private final ResourceTypeService resourceTypeService;

    public ResourceTypeController(ResourceTypeService resourceTypeService) {
        this.resourceTypeService = resourceTypeService;
    }

    @GetMapping("/query")
    public Response queryPage(@RequestHeader("X-Token") String token, ResourceTypeQueryVO vo) {
        TokenVerity verity = JwtUtil.verity(token);
        int enterpriseId = verity.getEnterpriseId();
        try {
            Page<ResourceType> page = resourceTypeService.queryPage(enterpriseId, vo);
            List<ResourceType> list = page.getContent();
            long total = page.getTotalElements();
            HashMap<String, Object> res = new HashMap<>();
            res.put("list", list);
            res.put("total", total);
            return new SuccessResponse(res);
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }

    @PostMapping("/create")
    public Response createResourceType(@RequestHeader("X-Token") String token, ResourceType resourceType) {
        TokenVerity verity = JwtUtil.verity(token);
        String username = verity.getUsername();
        int enterpriseId = verity.getEnterpriseId();
        resourceType.setCreateBy(username);
        try {
            Integer save = resourceTypeService.createResourceType(enterpriseId, resourceType);
            if (save == 1) {
                return new SuccessResponse("创建成功");
            } else {
                return new FailResponse(400, "创建失败");
            }
        } catch (Exception e) {
            return new FailResponse(500, e.getMessage());
        }
    }
}
