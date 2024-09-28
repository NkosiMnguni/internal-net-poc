package internalnetpoc.entity.relationship;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import internalnetpoc.entity.node.Guild;
import lombok.Getter;


@Getter
@RelationshipProperties
public class GuildMember {
    @Id
    @GeneratedValue
    private Long id;
    @TargetNode
    Guild guild;

    @Override
    public String toString() {
        return "guild=" + guild.getGuildName() +
                '}';
    }
}
