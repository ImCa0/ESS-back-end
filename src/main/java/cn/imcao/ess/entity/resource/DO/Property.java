package cn.imcao.ess.entity.resource.DO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

/**
 * @author ImCaO
 * @description 属性实体
 * @date Created at 2021/12/23 10:04
 */

@Node
@Data
@Builder
public class Property {

    @Id
    @GeneratedValue
    private UUID uuid;

    @org.springframework.data.neo4j.core.schema.Property
    private String identifier;

    @org.springframework.data.neo4j.core.schema.Property
    private String name;

    @org.springframework.data.neo4j.core.schema.Property
    private String description;

    @org.springframework.data.neo4j.core.schema.Property
    private String unit;

    @org.springframework.data.neo4j.core.schema.Property
    private Boolean isPreset;
}
