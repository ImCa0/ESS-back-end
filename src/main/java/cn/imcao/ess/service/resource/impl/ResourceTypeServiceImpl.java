package cn.imcao.ess.service.resource.impl;

import cn.imcao.ess.entity.resource.DO.*;
import cn.imcao.ess.entity.resource.VO.ResourceTypeQueryVO;
import cn.imcao.ess.mapper.resource.EnterpriseRepository;
import cn.imcao.ess.mapper.resource.ResourceRepository;
import cn.imcao.ess.mapper.resource.ResourceTypeRepository;
import cn.imcao.ess.service.resource.ResourceTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ImCaO
 * @description 制造资源类型服务实现
 * @date Created at 2021/12/23 10:21
 */

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    private final ResourceTypeRepository resourceTypeRepository;

    private final EnterpriseRepository enterpriseRepository;

    private final ResourceRepository resourceRepository;

    public ResourceTypeServiceImpl(ResourceTypeRepository resourceTypeRepository, EnterpriseRepository enterpriseRepository, ResourceRepository resourceRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Page<ResourceType> queryPage(Integer enterpriseId, ResourceTypeQueryVO vo) {
        vo.setName(vo.getName() == null ? "" : vo.getName());
        vo.setTag(vo.getTag() == null ? "" : vo.getTag());
        vo.setType(vo.getType() == null ? "" : vo.getType());
        Sort sort = Sort.unsorted();
        if ("+id".equals(vo.getSort())) {
            sort = Sort.by("n.createAt").ascending();
        } else if ("-id".equals(vo.getSort())) {
            sort = Sort.by("n.createAt").descending();
        }
        PageRequest pageRequest = PageRequest.of(vo.getPage() - 1, vo.getLimit(), sort);
        return resourceTypeRepository.queryPage(enterpriseId, vo.getName(), vo.getTag(), vo.getType(), pageRequest);
    }

    @Override
    public Integer createResourceType(Integer enterpriseId, ResourceType resourceType) {
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(enterpriseId);
        if (enterpriseOptional.isPresent()) {
            Enterprise enterprise = enterpriseOptional.get();
            enterprise.getResourceTypes().add(resourceType);
            enterpriseRepository.save(enterprise);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer updateResourceType(ResourceType resourceType) {
        if (resourceTypeRepository.findById(resourceType.getUuid()).isPresent()) {
            resourceTypeRepository.save(resourceType);
            return 1;
        }
        return 0;
    }

    @Override
    public String createProperty(String typeId, Property property) {
        Optional<ResourceType> resourceTypeOptional = resourceTypeRepository.findById(UUID.fromString(typeId));
        if (!resourceTypeOptional.isPresent()) {
            return "资源类型不存在";
        } else {
            ResourceType resourceType = resourceTypeOptional.get();
            resourceType.getProperties().add(property);
            resourceTypeRepository.save(resourceType);
            List<Resource> resourceList = resourceRepository.findAllByType(typeId);
            // 通过 findAllByType 查询到的资源信息不完整，需要重新通过 findById 补充，否则直接 save 会丢失信息
            for (int i = 0; i < resourceList.size(); i++) {
                Optional<Resource> resourceOptional = resourceRepository.findById(resourceList.get(i).getUuid());
                if (resourceOptional.isPresent()) {
                    resourceList.set(i, resourceOptional.get());
                }
            }
            for (Resource resource : resourceList) {
                resource.getHasProperties().add(HasProperty.builder().property(property).value("").queryUrl("").lastModifiedAt(0L).build());
                resourceRepository.save(resource);
            }
            return "属性创建成功";
        }
    }
}
