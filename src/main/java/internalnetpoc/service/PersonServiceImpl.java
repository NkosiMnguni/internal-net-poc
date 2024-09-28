package internalnetpoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.Neo4jVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internalnetpoc.dao.PersonDao;
import internalnetpoc.dto.PersonDTO;
import internalnetpoc.entity.node.Person;
import internalnetpoc.repository.PersonRepository;


@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final EmbeddingModel embeddingModel;
    private final Neo4jVectorStore vectorStore;
    private final VectorStore springVectorStore;
    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, Neo4jVectorStore vectorStore, EmbeddingModel embeddingModel, VectorStore springVectorStore) {
        this.springVectorStore = springVectorStore;
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
        this.personRepository = personRepository;
    }
    public List<Person> getAllPerson() {
        return personRepository.findAllPerson();
    }
    public Person findPersonByFullName(String fullName) {
        return personRepository.findPersonByFullName(fullName);
    }

    @Override
    public void loadAllPersonsToRedisVDB() {
        List<Document> documents = new ArrayList<>();
        personRepository.findAllPerson().forEach(person -> documents.add(new Document(person.toString())));
        vectorStore.accept(documents);

        // Add the documents to Redis
        vectorStore.add(documents);
    }
}
