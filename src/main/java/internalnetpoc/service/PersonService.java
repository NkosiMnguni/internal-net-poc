package internalnetpoc.service;

import java.util.List;

import internalnetpoc.dto.PersonDTO;
import internalnetpoc.entity.node.Person;


public interface PersonService {
    List<Person> getAllPerson();
    public Person findPersonByFullName(String fullName);

    void loadAllPersonsToRedisVDB();
}
