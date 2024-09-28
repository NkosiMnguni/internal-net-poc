package internalnetpoc.entity.relationship;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import internalnetpoc.entity.node.Skill;
import lombok.Getter;


@Getter
@RelationshipProperties
public class PersonSkill {
    @Id
    @GeneratedValue
    private Long id;
    private double rating;
    @TargetNode
    private Skill skill;

    @Override
    public String toString() {
        return "skillName=" + skill.getSkillName() +
        ", rating=" + rating + "/5";
    }
}