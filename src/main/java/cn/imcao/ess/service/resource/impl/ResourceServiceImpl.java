package cn.imcao.ess.service.resource.impl;

import cn.imcao.ess.entity.resource.DO.Resource;
import cn.imcao.ess.entity.resource.VO.ResourceQueryVO;
import cn.imcao.ess.mapper.resource.ResourceRepository;
import cn.imcao.ess.mapper.resource.ResourceTypeRepository;
import cn.imcao.ess.service.resource.ResourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author ImCaO
 * @description 制造资源实例服务实现
 * @date Created at 2021/12/27 10:31
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceTypeRepository resourceTypeRepository, ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
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
    public Integer createResource(Integer enterpriseId, Resource resource) {
        return null;
    }

    @Override
    public Integer updateResource(Resource resource) {
        return null;
    }
}
