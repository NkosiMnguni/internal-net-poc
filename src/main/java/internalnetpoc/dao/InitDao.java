package internalnetpoc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.exceptions.ClientException;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.Neo4jVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import internalnetpoc.dto.PersonDTO;
import internalnetpoc.dto.RoleDTO;
import internalnetpoc.repository.PersonRepository;


@Repository
public class InitDao {

    final SessionFactory sessionFactory;
    final Driver driver;
    final PersonRepository personRepository;
    final Neo4jVectorStore vectorStore;

    @Autowired
    public InitDao(SessionFactory sessionFactory, PersonRepository personRepository, Neo4jVectorStore vectorStore, Driver driver) {
        this.driver = driver;
        this.sessionFactory = sessionFactory;
        this.personRepository = personRepository;
        this.vectorStore = vectorStore;
    }
    public void savePerson(PersonDTO personDTO, RoleDTO roleDTO, List<String> skills,List<String> projects, String guild){
        Session session = sessionFactory.openSession();
        String cypher = """
                    MERGE (person:Person {empId: $empId, name: $full_name, email: $email, office_code: $office_code, managerEmpId: $managerEmpId})
                    MERGE (role: Role {name: $role_name})
                    MERGE (person) -[:HAS_ROLE]-> (role)
                    MERGE (guild:Guild {name: $guild})
                    MERGE (person)-[:PART_OF]->(guild)
                    WITH person
                    CALL {
                        WITH person
                        UNWIND [x IN $skills] AS skill_name
                            MERGE (skill: Skill {name: skill_name})
                            MERGE (person) -[:HAS_SKILL {rating: toInteger(rand() * 10)}]-> (skill)
                    }
                    WITH person
                    CALL {
                        WITH person
                        UNWIND [x IN $projects] AS project_name
                            MERGE (project: Project {name: project_name})
                            MERGE (person) -[:CONTRIBUTES_TO{numOfContributions: toInteger(rand() * 101) + 50}]-> (project)}
                """;
        session.query(cypher, Map.of("full_name", personDTO.fullName(),
                "email", personDTO.email(),
                "empId", personDTO.empId(),
                "office_code", personDTO.officeCode(),
                "managerEmpId", personDTO.managerEmployeeNumber(),
                "role_name", roleDTO.name(),
                "skills", skills,
                "projects", projects,
                "guild", guild,
                "skillRating", 3 + (Math.random() * 2)
        ));
    }

    public void createManagerRelationship() {
        Session session = sessionFactory.openSession();
        String cypher = """
                MATCH (person:Person WHERE person.managerEmpId IS NOT NULL)
                    WITH person
                    CALL {
                        WITH person
                        MATCH (manager: Person {empId: person.managerEmpId})
                        MERGE (person) -[:REPORTS_TO]->(manager)
                    }
                """;
        session.query(cypher, Map.of());

    }
    public void createInteractions() {
        Session session = sessionFactory.openSession();
        String cypher = """
                    MATCH (p1:Person), (p2:Person)
                    WHERE id(p1) < id(p2)
                    WITH p1, p2, rand() AS r
                    WHERE r < 0.6  // This will create relationships for about 60% of the pairs
                    WITH p1, p2, rand() * 10 AS randomInteractions
                    CREATE (p1)-[r:INTERACTS_WITH {avgNumOfInteractionsPerDay: round(randomInteractions * 100) / 100}]->(p2)
                    RETURN p1.name, p2.name, r.avgNumOfInteractionsPerDay
                """;
        session.query(cypher, Map.of());
    }
    public void loadVectorStore() {
        List<Document> documents = new ArrayList<>();
        personRepository.findAllPerson().forEach(person -> documents.add(new Document(person.toString())));
        vectorStore.accept(documents);

        // Add the documents to Redis
        vectorStore.add(documents);
    }
    public void executeCypher(String cypher) throws ClientException {
        var result = driver.executableQuery(cypher).execute();
        System.out.println(result.records());
    }

    public void addRepositories() {

    }
}
