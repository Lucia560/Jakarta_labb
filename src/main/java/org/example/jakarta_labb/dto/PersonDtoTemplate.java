package org.example.jakarta_labb.dto;

import org.example.jakarta_labb.entity.PersonTemplate;

public record PersonDtoTemplate(String name, int age) {

    public static PersonDtoTemplate map(PersonTemplate person) {
        return new PersonDtoTemplate(person.getName(), person.getAge());
    }

    public static PersonTemplate map(PersonDtoTemplate personDto) {
        var person = new PersonTemplate();
        person.setName(personDto.name);
        person.setAge(personDto.age);
        return person;
    }
}
