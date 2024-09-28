package internalnetpoc.dao;

import java.util.List;
import java.util.Map;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import internalnetpoc.dto.PersonSkillDTO;


@Repository
public class SkillDao {
    final SessionFactory sessionFactory;
    @Autowired
    public SkillDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PersonSkillDTO> getSkillRatings() {
        Session session = sessionFactory.openSession();
        return session.queryDto(
                "MATCH (person) -[r:HAS_SKILL]-> (s:Skill) RETURN person.name AS personName, s.name AS skillName, r.rating AS rating ORDER BY r.rating DESC",
                Map.of(),
                PersonSkillDTO.class
        );
    }
}
