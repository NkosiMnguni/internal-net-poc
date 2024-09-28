package internalnetpoc.entity.node;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Getter;


@Node
@Getter
public class Role {
    @Id @GeneratedValue
    private Long id;
    @Property("name")
    private String roleName;

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName;
    }
}
