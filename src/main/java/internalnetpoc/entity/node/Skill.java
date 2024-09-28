package internalnetpoc.entity.node;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Getter;


@Getter
@Node
public class Skill {
    @Id
    @GeneratedValue
    private Long id;
    @Property("name")
    private String skillName;

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                '}';
    }
}
