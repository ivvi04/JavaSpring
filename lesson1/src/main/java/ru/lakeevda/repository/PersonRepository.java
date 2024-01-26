package ru.lakeevda.repository;

import ru.lakeevda.entity.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonRepository {
    private static List<Person> personList = new ArrayList<>();

    public static List<Person> getRepository () {
        return personList;
    }

    public static void setRepository (List<Person> personList) {
        personList = personList;
    }

    public static void addPerson (Person person) {
        personList.add(person);
    }

    public static void deletePerson (Person person) {
        personList.remove(person);
    }

}
