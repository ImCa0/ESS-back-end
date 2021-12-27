package cn.imcao.ess.controller;

import cn.imcao.ess.entity.resource.DO.Resource;
import cn.imcao.ess.entity.resource.VO.ResourceQueryVO;
import cn.imcao.ess.entity.response.FailResponse;
import cn.imcao.ess.entity.response.Response;
import cn.imcao.ess.entity.response.SuccessResponse;
import cn.imcao.ess.entity.user.TokenVerity;
import cn.imcao.ess.service.resource.ResourceService;
import cn.imcao.ess.utils.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
