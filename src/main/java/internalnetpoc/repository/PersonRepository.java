package internalnetpoc.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import internalnetpoc.entity.node.Person;


public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("""
    MATCH (person:Person { name: $fullName })-[r:HAS_SKILL]->(skill:Skill)
    OPTIONAL MATCH (person)-[m:REPORTS_TO]->(manager:Person)
    OPTIONAL MATCH (person)-[i:INTERACTS_WITH]->(otherPerson:Person)
    OPTIONAL MATCH (person)-[g:PART_OF]->(guild:Guild)
    OPTIONAL MATCH (person)-[c:CONTRIBUTES_TO]->(project:Project)
    OPTIONAL MATCH (person)-[ro:HAS_ROLE]->(role:Role)
    RETURN person, collect(r), collect(skill), collect(m), collect(manager), collect(i), collect(otherPerson), collect(g), collect(guild), collect(c), collect(project), collect(ro), collect(role)
    """)
    Person findPersonByFullName(String fullName);
    @Query("""
    MATCH (person:Person)-[r:HAS_SKILL]->(skill:Skill)
    OPTIONAL MATCH (person)-[m:REPORTS_TO]->(manager:Person)
    OPTIONAL MATCH (person)-[i:INTERACTS_WITH]->(otherPerson:Person)
    OPTIONAL MATCH (person)-[g:PART_OF]->(guild:Guild)
    OPTIONAL MATCH (person)-[c:CONTRIBUTES_TO]->(project:Project)
    OPTIONAL MATCH (person)-[ro:HAS_ROLE]->(role:Role)
    RETURN person, collect(r), collect(skill), collect(m), collect(manager), collect(i), collect(otherPerson), collect(g), collect(guild), collect(c), collect(project), collect(ro), collect(role)
    """)
    List<Person> findAllPerson();
}
