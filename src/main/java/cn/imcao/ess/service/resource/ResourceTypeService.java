package cn.imcao.ess.service.resource;

import cn.imcao.ess.entity.resource.DO.HasProperty;
import cn.imcao.ess.entity.resource.DO.Property;
import cn.imcao.ess.entity.resource.DO.ResourceType;
import cn.imcao.ess.entity.resource.VO.ResourceTypeQueryVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ImCaO
 * @description 制造资源类型服务接口
 * @date Created at 2021/12/23 10:19
 */
public interface ResourceTypeService {

    Page<ResourceType> queryPage(Integer enterpriseId, ResourceTypeQueryVO resourceTypeQueryVO);

    ResourceType queryById(String id);

    Integer createResourceType(Integer enterpriseId, ResourceType resourceType);

    Integer updateResourceType(ResourceType resourceType);

    void deleteResourceType(ResourceType resourceType);

    List<Property> queryPresetProperty();

    String createProperty(String typeId, Property property);

    void updateProperty(Property property);

    void deleteProperty(Property property);
}
