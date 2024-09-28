package internalnetpoc.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Repository;

import internalnetpoc.repository.PersonRepository;
import internalnetpoc.entity.node.Person;


@Repository
public class PersonDao {

    final SessionFactory sessionFactory;
    private final PersonRepository personRepository;
    @Autowired
    public PersonDao(SessionFactory sessionFactory, PersonRepository personRepository) {
        this.sessionFactory = sessionFactory;
        this.personRepository = personRepository;
    }
    public List<Person> getAllPersons() {
        Session session = sessionFactory.openSession();
        Collection<Person> people = session.loadAll(Person.class);
        return new ArrayList<>(people);
    }
}
