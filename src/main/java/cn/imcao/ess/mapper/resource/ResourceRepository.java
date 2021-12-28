package cn.imcao.ess.mapper.resource;

import cn.imcao.ess.entity.resource.DO.HasProperty;
import cn.imcao.ess.entity.resource.DO.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * @author ImCaO
 * @description 制造资源实例库
 * @date Created at 2021/12/27 11:06
 */
public interface ResourceRepository extends Neo4jRepository<Resource, UUID> {

    @Query(value = "MATCH (:Enterprise{enterpriseId:$enterpriseId})" +
            "-[:HAS_RESOURCE_TYPE]->(t:ResourceType)" +
            "-[r1:HAS_RESOURCE]->(n:Resource) " +
            "WHERE n.name CONTAINS $name " +
            "AND n.identifier CONTAINS $identifier " +
            "AND t.name CONTAINS $type " +
            "AND (n.isShared = $isShared OR $isShared IS NULL)" +
            "OPTIONAL MATCH (n)-[r2]->(p1:Property) " +
            "OPTIONAL MATCH (t)-[r3]->(p2:Property) " +
            "RETURN n, collect(r1), collect(t), collect(r2), collect(p1), collect(r3), collect(p2) " +
            ":#{orderBy(#pageable)} SKIP $skip LIMIT $limit",
            countQuery = "MATCH (:Enterprise{enterpriseId:$enterpriseId})" +
                    "-[:HAS_RESOURCE_TYPE]->(t:ResourceType)" +
                    "-[:HAS_RESOURCE]->(n:Resource) " +
                    "WHERE n.name CONTAINS $name " +
                    "AND n.identifier CONTAINS $identifier " +
                    "AND t.name CONTAINS $type " +
                    "AND (n.isShared = $isShared OR $isShared IS NULL)" +
                    "RETURN count(n)")
    Page<Resource> queryPage(@Param("enterpriseId") Integer enterpriseId,
                             @Param("name") String name,
                             @Param("identifier") String identifier,
                             @Param("type") String type,
                             @Param("isShared") Boolean isShared,
                             Pageable pageable);

    @Query("match ()-[r:HAS_PROPERTY]->() " +
            "where id(r) = :#{#property.id} " +
            "set r.value = :#{#property.value}, " +
            "r.queryUrl = :#{#property.queryUrl}, " +
            "r.lastModifiedAt = :#{#property.lastModifiedAt} " +
            "RETURN r")
    void updateProperty(@Param("property") HasProperty hasProperty);
}
