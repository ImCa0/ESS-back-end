package cn.imcao.ess.mapper.resource;

import cn.imcao.ess.entity.resource.DO.Property;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author ImCaO
 * @description 属性库
 * @date Created at 2022/1/5 16:35
 */
public interface PropertyRepository extends Neo4jRepository<Property, UUID> {

    List<Property> findAllByIsPreset(Boolean isPreset);
}
