package internalnetpoc.entity.node;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import internalnetpoc.entity.relationship.Contribution;
import internalnetpoc.entity.relationship.GuildMember;
import internalnetpoc.entity.relationship.Interaction;
import internalnetpoc.entity.relationship.Manager;
import internalnetpoc.entity.relationship.PersonSkill;
import internalnetpoc.entity.relationship.RoleOccupant;
import lombok.Getter;


@Node
@Getter
public class Person {
    @Id @GeneratedValue
    private Long id;

    @Property("name")
    private String fullName;
    private String email;
    @Property("empId")
    private String employeeNumber;
    @Property("office_code")
    private String officeCode;
    @Relationship(type = "REPORTS_TO", direction = Relationship.Direction.OUTGOING)
    private Manager manager;
    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private RoleOccupant roleOccupant;
    @Relationship(type = "HAS_SKILL", direction = Relationship.Direction.OUTGOING)
    private List<PersonSkill> skills;
    @Relationship(type = "CONTRIBUTES_TO", direction = Relationship.Direction.OUTGOING)
    private List<Contribution> projects;
    @Relationship(type = "PART_OF", direction = Relationship.Direction.OUTGOING)
    private GuildMember guild;
    @Relationship(type = "INTERACTS_WITH", direction = Relationship.Direction.OUTGOING)
    private List<Interaction> interactions;

    @Override
    public String toString() {
        String summary = "";
        if (manager == null) {
            summary = fullName + " with email " + email + ", is a " + roleOccupant.getRole().getRoleName() + " at our " + officeCode + " office"
                    + " under nobody's supervision"  + ". They have the following skills: "+ skills + ", and are contributing to these projects: " + projects + ". They are a member of the " + guild.getGuild().getGuildName() + " guild. Here is the information related to their interactions "+ interactions;
        }
        else {
            summary = fullName + " with email " + email + ", is a " + roleOccupant.getRole().getRoleName() + " at our " + officeCode + " office"
                    + " under the supervision of " + manager.getPerson().getFullName() + ". They have the following skills: "+ skills + ", and are contributing to these projects: " + projects + ". They are a member of the " + guild.getGuild().getGuildName() + " guild. Here is the information related to their interactions "+ interactions;
        }
        return summary;
    }
}
