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
public class SnsController extends Sns {

  @Autowired
  private SnsRepository snsRepository;
  /**
   * sns list
   *
   * @return list
   */
  @RequestMapping("/list")
  public List<Sns> list(Sns sns) {

    sns.setStatus("A");
    List<Sns> snsList = snsRepository.findByMemberIdAndStatus(sns.getMemberId(), sns.getStatus());

    return snsList;
  }

  /**
   * sns insert
   *
   * @return list
   */
  @RequestMapping("/insert")
  public List<Sns> insert(Sns sns) {

    snsRepository.save(sns);
    sns.setStatus("A");

    List<Sns> snsList = snsRepository.findByMemberIdAndStatus(sns.getMemberId(), sns.getStatus());

    return snsList;
  }

  /**
   * sns update
   *
   * @return list
   */
  @RequestMapping("/update")
  public List<Sns> update(Sns sns) {

    Sns findSns = snsRepository.findOne(sns.getId());

    sns.setId(findSns.getId());
    snsRepository.save(sns);

    List<Sns> snsList = snsRepository.findByMemberIdAndStatus(sns.getMemberId(), sns.getStatus());

    return snsList;
  }


  /**
   * sns delete
   *
   * @return list
   */
  @RequestMapping("/delete")
  public List<Sns> delete(Sns sns) {

    snsRepository.save(sns);
    sns.setStatus("A");

    List<Sns> snsList = snsRepository.findByMemberIdAndStatus(sns.getMemberId(), sns.getStatus());

    return snsList;
  }


}
