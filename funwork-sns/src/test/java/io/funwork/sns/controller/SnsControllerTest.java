package io.funwork.sns.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import io.funwork.FunworkSnsMain;
import io.funwork.authority.domain.Authority;
import io.funwork.authority.service.AuthorityService;
import io.funwork.parsing.service.ParsingService;
import io.funwork.sns.domain.Sns;
import io.funwork.sns.repository.SnsRepository;
import io.funwork.sns.service.SnsService;
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
  private SnsService snsService;

  @Autowired
  private ParsingService parsingService;

  @Autowired
  private AuthorityService authorityService;

  @Test
  public void testInsert() throws Exception {

    //given
    Sns sns = new Sns();
    sns.setNumber(1);
    sns.setEmail("urosaria0720@gmail.com");
    sns.setDeptCode(1);
    sns.setDeptName("dev");
    sns.setContent("금일 회식합니다.. @D[개발팀] 참석해주세요! from @P[urosaria0720@gmail.com] ps. @D[디자인팀] 함께 하셔도 됩니당^^  @P[master@test.co.kr] 회식비좀 지원해주세요!!");
    log.info("sns::" + sns.toString());

    //when
    sns = snsService.saveSns(sns);

    //auth set
    Authority authority = new Authority();
    authority.setSns(sns);
    String authList = parsingService.listParsing(sns.getContent());

    //contents -> parsing -> authList set
    if(authList!=null){
      authority.setAuthList(authList);
    }

    //auth insert
    authority = authorityService.saveAuthority(authority);
    log.info("auth :: " + authority.toString());

    //then
    List<Sns> snsList = snsService.listSns();

    //Sns newSns = snsService.showSns(sns.getNumber());

    log.info("snsList :: " + snsList.toString());
    //assertThat(newSns.getNumber(), is(sns.getNumber()));

  }

  @Test
  public void testUpdate() throws Exception {

    Long number;
    Sns sns = new Sns();

    //given
    number=(long)1;
    sns.setNumber(number);
    sns.setDeptCode(1);
    sns.setDeptName("dev");
    sns.setContent("수정합니다... 금일 회식합니다!! 장소는 회사앞 삼겹살집 입니다.  @D[개발팀] 참석해주세요! from @P[urosaria0720@gmail.com] ps. @D[디자인팀] 함께 하셔도 됩니당^^  @P[master@test.co.kr] 회식비좀 지원해주세요!!");
    sns.setEmail("urosaria0720@gmail.com");
    sns.setUseYn("Y");

    //when
    sns = snsService.saveSns(sns);
    log.info("sns :: " + sns.toString());

    //auth set
    Authority authority = new Authority();
    authority.setSns(sns);
    String authList = parsingService.listParsing(sns.getContent());

    //contents -> parsing -> authList set
    if(authList!=null){
      authority.setAuthList(authList);
    }

    //auth insert
    authority = authorityService.saveAuthority(authority);
    log.info("auth :: " + authority.toString());

    //then
    Sns newSns = snsService.showSns(sns.getNumber());

    log.info("newsns :: " + newSns.toString());
    assertThat(newSns.getNumber(), is(sns.getNumber()));

  }

}