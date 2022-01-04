package cn.imcao.ess.service.resource;

import cn.imcao.ess.entity.resource.DO.HasProperty;
import cn.imcao.ess.entity.resource.DO.Resource;
import cn.imcao.ess.entity.resource.VO.ResourceQueryVO;
import org.springframework.data.domain.Page;

/**
 * @author ImCaO
 * @description 制造资源实例服务接口
 * @date Created at 2021/12/27 10:24
 */
public interface ResourceService {

    Page<Resource> queryPage(Integer enterpriseId, ResourceQueryVO resourceQueryVO);

    void createResource(Resource resource);

    Integer updateResource(Resource resource);

    void deleteResource(Resource resource);

    void updateProperty(HasProperty hasProperty);
}
