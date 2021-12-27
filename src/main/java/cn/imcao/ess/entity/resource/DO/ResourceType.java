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
 * @description 制造资源类型实体
 * @date Created at 2021/12/23 10:02
 */

@Node
@Data
@Builder
public class ResourceType {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Property
    private String name;

    @Property
    private String tag;

    @Property
    private String type;

    @Property
    private String description;

    @Property
    private String createBy;

    @CreatedDate
    private Long createAt;

    @Relationship(type = "HAS_PRESET_PROPERTY", direction = Relationship.Direction.OUTGOING)
    private List<cn.imcao.ess.entity.resource.DO.Property> properties;
}
