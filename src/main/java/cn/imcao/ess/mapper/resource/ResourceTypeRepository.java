package cn.imcao.ess.mapper.resource;

import cn.imcao.ess.entity.resource.DO.ResourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * @author ImCaO
 * @description 制造资源类型库
 * @date Created at 2021/12/23 9:58
 */

public interface ResourceTypeRepository extends Neo4jRepository<ResourceType, UUID> {

    @Query(value = "MATCH (:Enterprise{enterpriseId:$enterpriseId})-[:HAS_RESOURCE_TYPE]->(n:ResourceType) " +
            "WHERE n.name CONTAINS $name AND n.tag CONTAINS $tag AND n.type CONTAINS $type " +
            "OPTIONAL MATCH (n)-[r]->(m:Property) " +
            "RETURN n, collect(r), collect(m) :#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (:Enterprise{enterpriseId:$enterpriseId})-[:HAS_RESOURCE_TYPE]->(n:ResourceType) " +
                    "WHERE n.name CONTAINS $name AND n.tag CONTAINS $tag AND n.type CONTAINS $type " +
                    "RETURN count(n)")
    Page<ResourceType> queryPage(@Param("enterpriseId") Integer enterpriseId,
                                 @Param("name") String name,
                                 @Param("tag") String tag,
                                 @Param("type") String type,
                                 Pageable pageable);
}
