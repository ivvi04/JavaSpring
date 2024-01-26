package ru.lakeevda;

import ru.lakeevda.entity.Person;
import ru.lakeevda.service.PersonService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
        personService.addPerson("Денис", "Лакеев", 34);
        personService.addPerson("Анна", "Борисова", 32);
        List<Person> personList = personService.getRepository();
        Person person1 = personList.get(0);
        Person person2 = personList.get(1);
        System.out.println(person1.equals(person2));
        System.out.println(person2.hashCode());
        System.out.println(person2);

        Person person3 = new Person("Андрей", "Лакеев", 59);
        String s = personService.writeToJson(person3);
        System.out.println(s);

        person3 = personService.readFromJson(s);
        System.out.println(person3);

    }
}