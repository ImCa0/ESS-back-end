package cn.imcao.ess.entity.resource.DO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.Property;


import java.util.List;
import java.util.UUID;

/**
 * @author ImCaO
 * @description 制造资源实体
 * @date Created at 2021/12/23 10:03
 */

@Node
@Data
@Builder
public class Resource {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Property
    private String identifier;

    @Property
    private String name;

    @Property
    private String description;

    @Property
    private String brand;

    @Property
    private Boolean isShared;

    @Property
    private Integer price;

    @Property
    private String createBy;

    @CreatedDate
    private Long date;

    @Relationship(type = "HAS_RESOURCE", direction = Relationship.Direction.INCOMING)
    private ResourceType belongTo;

    @Relationship(type = "HAS_PROPERTY", direction = Relationship.Direction.OUTGOING)
    private List<HasProperty> hasProperties;
}
