package cn.imcao.ess.entity.resource.DO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author ImCaO
 * @description 属性关系实体
 * @date Created at 2021/12/23 10:03
 */

@RelationshipProperties
@Data
@Builder
public class HasProperty {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    private String queryUrl;

    @LastModifiedDate
    private Long lastModifiedAt;

    @TargetNode
    private Property property;
}
