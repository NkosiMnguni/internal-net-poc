package internalnetpoc.entity.relationship;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import internalnetpoc.entity.node.Role;
import lombok.Getter;


@Getter
@RelationshipProperties
public class RoleOccupant {
    @Id
    @GeneratedValue
    private Long id;
    @TargetNode
    private Role role;

    @Override
    public String toString() {
        return "role=" + role.getRoleName();
    }
}
