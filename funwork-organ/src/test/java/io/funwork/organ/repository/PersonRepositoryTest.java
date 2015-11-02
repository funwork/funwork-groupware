package io.funwork.organ.repository;

import io.funwork.FunworkOrgan;
import io.funwork.organ.domain.Person;
import io.funwork.organ.domain.SecurityGrade;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkOrgan.class)
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Test
  public void test_person_save() {

    //given
    Person person = new Person();
    person.setEmail("test1@funwork.com");
    person.setPasswd("@@test1!");
    person.setName("테스트");
    person.setSecurityGrade(SecurityGrade.ADMIN);

    //when
    personRepository.save(person);

    //then
    Person newPerson = personRepository.findOne(person.getEmail());
    log.info(newPerson.toString());
    assertThat(newPerson.getSecurityGrade(), is(person.getSecurityGrade()));
    assertThat(newPerson.getEmail(), is(person.getEmail()));
    assertThat(newPerson.getName(), is(person.getName()));
  }

  @Test(expected = TransactionSystemException.class)
  public void test_person_validate_required() {

    //given
    Person person = new Person();
    person.setEmail("test1@funwork.com");

    //when
    personRepository.save(person);

    //then
    //필수값예외발생
  }

  @Test(expected = TransactionSystemException.class)
  public void test_person_password_size(){

    //given
    Person person = new Person();
    person.setEmail("test1@funwork.com");
    person.setSecurityGrade(SecurityGrade.NORMAL);
    person.setName("tester1");
    person.setPasswd("1234");

    //when
    personRepository.save(person);

    //then
    //size 예외발생
  }
}