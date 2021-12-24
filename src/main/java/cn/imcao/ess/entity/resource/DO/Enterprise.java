package cn.imcao.ess.entity.resource.DO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @author ImCaO
 * @description 企业实体
 * @date Created at 2021/12/23 10:01
 */

@Node
@Data
@Builder
public class Enterprise {

    @Id
    private Integer enterpriseId;

    @Relationship(type = "HAS_RESOURCE_TYPE", direction = Relationship.Direction.OUTGOING)
    private List<ResourceType> resourceTypes;
}
