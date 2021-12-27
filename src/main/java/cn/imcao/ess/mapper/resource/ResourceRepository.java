package cn.imcao.ess.mapper.resource;

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
            "OPTIONAL MATCH (n)-[r2]->(p:Property) " +
            "RETURN n, collect(r1), collect(t), collect(r2), collect(p) " +
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
}
