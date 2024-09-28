package internalnetpoc.entity.relationship;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import internalnetpoc.entity.node.Project;
import lombok.Getter;


@Getter
@RelationshipProperties
public class Contribution {
    @Id
    @GeneratedValue
    private Long id;
    private int numOfContributions;
    @TargetNode
    private Project project;

    @Override
    public String toString() {
        return project.getProjectName();
    }
}
