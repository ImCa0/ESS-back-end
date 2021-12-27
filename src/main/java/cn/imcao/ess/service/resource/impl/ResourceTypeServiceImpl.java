package cn.imcao.ess.service.resource.impl;

import cn.imcao.ess.entity.resource.DO.Enterprise;
import cn.imcao.ess.entity.resource.DO.ResourceType;
import cn.imcao.ess.entity.resource.VO.ResourceTypeQueryVO;
import cn.imcao.ess.mapper.resource.EnterpriseRepository;
import cn.imcao.ess.mapper.resource.ResourceTypeRepository;
import cn.imcao.ess.service.resource.ResourceTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ImCaO
 * @description 制造资源类型服务实现
 * @date Created at 2021/12/23 10:21
 */

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    private final ResourceTypeRepository resourceTypeRepository;

    private final EnterpriseRepository enterpriseRepository;

    public ResourceTypeServiceImpl(ResourceTypeRepository resourceTypeRepository, EnterpriseRepository enterpriseRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
        this.enterpriseRepository = enterpriseRepository;
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
}
