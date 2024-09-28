package internalnetpoc.entity.node;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Getter;


@Getter
@Node
public class Guild {
    @Id
    @GeneratedValue
    private Long id;
    @Property("name")
    private String guildName;

    @Override
    public String toString() {
        return "Guild{" +
                "guildName='" + guildName;
    }
}
