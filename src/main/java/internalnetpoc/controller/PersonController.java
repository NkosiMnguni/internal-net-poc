package internalnetpoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import internalnetpoc.entity.node.Person;
import internalnetpoc.service.PersonService;


@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("person/all")
    public List<Person> getAllPersons() {
        List<Person> allPersons = personService.getAllPerson();
        return allPersons;
    }
    @GetMapping("person/fullName")
    public Person getPersonByName(@RequestParam(value = "fullName", defaultValue = "Nkosi Mnguni") String fullName) {
        Person person = personService.findPersonByFullName(fullName);
        System.out.println(person);
        return person;
    }
    @GetMapping("person/load-all-to-vdb")
    public void loadAllPersonsToVDB() {
        personService.loadAllPersonsToRedisVDB();
    }
}
