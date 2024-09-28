package internalnetpoc.entity.relationship;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import internalnetpoc.entity.node.Person;
import lombok.Getter;


@Getter
@RelationshipProperties
public class Interaction {
    @Id
    @GeneratedValue
    private Long id;
    private double avgNumOfInteractionsPerDay;
    @TargetNode
    private Person person;

    @Override
    public String toString() {
        return "avgNumOfInteractionsPerDay=" + avgNumOfInteractionsPerDay +
                ", otherPerson=" + person.getFullName();
    }
}