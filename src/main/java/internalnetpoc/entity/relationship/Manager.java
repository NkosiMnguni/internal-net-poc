package internalnetpoc.entity.relationship;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import internalnetpoc.entity.node.Person;
import lombok.Getter;


@Getter
@RelationshipProperties
public class Manager {
    @Id
    @GeneratedValue
    private Long id;
    @TargetNode
    private Person person;

    @Override
    public String toString() {
        return "fullName=" + person.getFullName();
    }
}
