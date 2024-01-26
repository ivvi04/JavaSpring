package ru.lakeevda.service;

import com.google.gson.Gson;
import ru.lakeevda.entity.Person;
import ru.lakeevda.repository.PersonRepository;

import java.util.List;

public class PersonService {
    public List<Person> getRepository() {
        return PersonRepository.getRepository();
    }

    public void addPerson(String firstName, String lastName, int age) {
        Person person = new Person(firstName, lastName, age);
        addPerson(person);
    }

    public void addPerson(Person person) {
        PersonRepository.addPerson(person);
    }

    public Person getPersonById(int id) {
        Person result = null;
        List<Person> personList = PersonRepository.getRepository().stream()
                .filter(x -> x.getId() == id).toList();
        if (!personList.isEmpty()) result = personList.get(0);
        return result;
    }

    public String writeToJson (Person person) {
        Gson gson = new Gson();
        return gson.toJson(person);
    }

    public Person readFromJson (String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Person.class);
    }
}
