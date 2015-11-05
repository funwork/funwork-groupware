package io.funwork.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.funwork.authority.domain.Authority;
import io.funwork.authority.repository.AuthorityRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by urosaria on 2015. 11. 5..
 */

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthorityController {

  @Autowired
  private AuthorityRepository authorityRepository;

  /**
   * auth insert
   *
   * @return list
   */
  @RequestMapping("/insert")
  public void insert(Authority auth) {

    authorityRepository.save(auth);

  }


  /**
   * auth delete
   *
   * @return list
   */
  @RequestMapping("/delete")
  public void delete(Authority auth) {

    authorityRepository.save(auth);

  }


}
