package cn.imcao.ess.service.resource.impl;

import cn.imcao.ess.entity.resource.DO.*;
import cn.imcao.ess.entity.resource.VO.ResourceQueryVO;
import cn.imcao.ess.mapper.resource.ResourceRepository;
import cn.imcao.ess.mapper.resource.ResourceTypeRepository;
import cn.imcao.ess.service.resource.ResourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ImCaO
 * @description 制造资源实例服务实现
 * @date Created at 2021/12/27 10:31
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    private final ResourceTypeRepository resourceTypeRepository;

    public ResourceServiceImpl(ResourceTypeRepository resourceTypeRepository, ResourceRepository resourceRepository, ResourceTypeRepository resourceTypeRepository1) {
        this.resourceRepository = resourceRepository;
        this.resourceTypeRepository = resourceTypeRepository1;
    }

    @Override
    public Page<Resource> queryPage(Integer enterpriseId, ResourceQueryVO vo) {
        vo.setName(vo.getName() == null ? "" : vo.getName());
        vo.setIdentifier(vo.getIdentifier() == null ? "" : vo.getIdentifier());
        vo.setType(vo.getType() == null ? "" : vo.getType());
        Sort sort = Sort.unsorted();
        if ("+id".equals(vo.getSort())) {
            sort = Sort.by("n.identifier").ascending();
        } else if ("-id".equals(vo.getSort())) {
            sort = Sort.by("n.identifier").descending();
        }
        PageRequest pageRequest = PageRequest.of(vo.getPage() - 1, vo.getLimit(), sort);
        return resourceRepository.queryPage(enterpriseId, vo.getName(), vo.getIdentifier(), vo.getType(), vo.getIsShared(), pageRequest);
    }

    @Override
    public Integer createResource(UUID resourceTypeId, Resource resource) {
        Optional<ResourceType> resourceTypeOptional = resourceTypeRepository.findById(resourceTypeId);
        if (resourceTypeOptional.isPresent()) {
            ResourceType resourceType = resourceTypeOptional.get();
            resource.setType(resourceType);
            resource.setHasProperties(new ArrayList<>());
            for (Property property : resourceType.getProperties()) {
                resource.getHasProperties().add(HasProperty.builder().property(property).value("").queryUrl("").lastModifiedAt(0L).build());
            }
            resourceRepository.save(resource);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer updateResource(Resource resource) {
        if (resourceRepository.findById(resource.getUuid()).isPresent()) {
            resourceRepository.save(resource);
            return 1;
        }
        return 0;
    }

    public void updateProperty(HasProperty hasProperty) {
        hasProperty.setLastModifiedAt(new Date().getTime());
        if (hasProperty.getValue() == null) {
            hasProperty.setValue("");
        }
        if (hasProperty.getQueryUrl() == null) {
            hasProperty.setQueryUrl("");
        }
        resourceRepository.updateProperty(hasProperty);
    }
}
