package io.funwork.sns.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import io.funwork.FunworkSnsMain;
import io.funwork.sns.domain.Sns;
import io.funwork.sns.repository.SnsRepository;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by urosaria on 2015. 10. 31..
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkSnsMain.class)
public class SnsControllerTest {

  @Autowired
  private SnsRepository snsRepository;

  @Test
  public void testInsert() throws Exception {

    //given
    Sns sns = new Sns();
    sns.setEmail("urosaria0720@gmail.com");
    sns.setDeptCode(1);
    sns.setDeptName("dev");
    sns.setContent("test123");
    log.info(sns.toString());

    //when
    snsRepository.save(sns);

    //then
    Sns newSns = snsRepository.findOne(sns.getNumber());
    log.info(newSns.toString());
    assertThat(newSns.getNumber(), is(sns.getNumber()));

  }

  @Test
  public void testUpdate() throws Exception {

    //given
    Sns sns = new Sns();
    sns.setNumber(1);
    sns.setContent("update~!!!");
    sns.setEmail("urosaria0720@gmail.com");
    sns.setUseYn("Y");

    //when
    Sns findSns = snsRepository.findOne(sns.getNumber());
    sns.setNumber(findSns.getNumber());
    snsRepository.save(sns);

    //then
    Sns newSns = snsRepository.findOne(sns.getNumber());
    log.info(newSns.toString());
    assertThat(newSns.getNumber(), is(sns.getNumber()));

  }

}