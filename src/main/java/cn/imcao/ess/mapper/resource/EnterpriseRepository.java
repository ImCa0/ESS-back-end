package cn.imcao.ess.mapper.resource;

import cn.imcao.ess.entity.resource.DO.Enterprise;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author ImCaO
 * @description 企业库
 * @date Created at 2021/12/24 10:22
 */
public interface EnterpriseRepository extends Neo4jRepository<Enterprise, Integer> {
}
