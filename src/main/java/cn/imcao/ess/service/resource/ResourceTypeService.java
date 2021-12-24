package cn.imcao.ess.service.resource;

import cn.imcao.ess.entity.resource.DO.ResourceType;
import cn.imcao.ess.entity.resource.VO.ResourceTypeQueryVO;
import org.springframework.data.domain.Page;

/**
 * @author ImCaO
 * @description 制造资源类型服务接口
 * @date Created at 2021/12/23 10:19
 */
public interface ResourceTypeService {

    Page<ResourceType> queryPage(Integer enterpriseId, ResourceTypeQueryVO resourceTypeQueryVO);
}
