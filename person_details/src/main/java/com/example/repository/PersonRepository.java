package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Person;

public interface PersonRepository extends CrudRepository<Person,Integer> {
@SuppressWarnings("unchecked")
Person save(Person person);
Person findByPersonId(int person_id);
}
