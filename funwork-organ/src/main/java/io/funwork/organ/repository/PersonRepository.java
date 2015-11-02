package io.funwork.organ.repository;

import io.funwork.organ.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String>{
}
