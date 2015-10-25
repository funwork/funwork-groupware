package io.funwork.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.funwork.sns.repository.SnsRepository;
import lombok.extern.slf4j.Slf4j;
import io.funwork.sns.domain.Sns;

/**
 * Created by urosaria on 2015. 10. 23..
 */
@RestController
@RequestMapping("/sns")
@Slf4j
public class SnsController {

  @Autowired
  private SnsRepository snsRepository;

  /**
   * sns list
   *
   * @return list
   */
  @RequestMapping("/list")
  public List<Sns> list() {
    log.debug("Test {} s", ">_<");
    Sns sns = new Sns();
    sns.setMemberId("urosaria");

    List<Sns> snsList = snsRepository.findByMemberId(sns.getMemberId());

    return snsList;
  }

  /**
   * sns insert
   *
   * @return list
   */
  @RequestMapping("/insert")
  public List<Sns> insert(Sns sns) {

    //snsRepository.save(sns);

    Sns sns1 = new Sns();
    sns1.setMemberId("urosaria");
    sns1.setContent("test123");
    snsRepository.save(sns1);

    Sns sns2 = new Sns();
    sns2.setMemberId("urosaria");
    sns2.setContent("test456");
    snsRepository.save(sns2);

    sns.setMemberId("urosaria");

    List<Sns> snsList = snsRepository.findByMemberId(sns.getMemberId());

    return snsList;
  }

  /**
   * sns update
   *
   * @return list
   */
  @RequestMapping("/update")
  public List<Sns> update(Sns sns) {

    sns.setId(1);
    sns.setContent("update~!!!");
    sns.setMemberId("urosaria");

    Sns findSns = snsRepository.findOne(sns.getId());

    sns.setId(findSns.getId());
    snsRepository.save(sns);

    List<Sns> snsList = snsRepository.findByMemberId(sns.getMemberId());

    return snsList;
  }


  /**
   * sns delete
   *
   * @return list
   */
  @RequestMapping("/delete")
  public List<Sns> delete(Sns sns) {

    sns.setId(1);
    sns.setStatus("D");
    sns.setMemberId("urosaria");
    snsRepository.save(sns);

    List<Sns> snsList = snsRepository.findByMemberId(sns.getMemberId());

    return snsList;
  }



}
