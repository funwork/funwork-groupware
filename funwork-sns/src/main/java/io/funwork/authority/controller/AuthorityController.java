package io.funwork.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import io.funwork.authority.domain.Authority;
import io.funwork.authority.service.AuthorityService;
import io.funwork.parsing.service.ParsingService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by urosaria on 2015. 11. 5..
 */

@RestController
@RequestMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AuthorityController {

  @Autowired
  private AuthorityService authorityService;

  @Autowired
  private ParsingService parsingService;

  /**
   * 권한 생성
   *
   * @return Authority
   */
  @RequestMapping(value = "/insert/{snsNumber}", method = RequestMethod.POST)
  @ResponseBody
  public Authority createAuthority(@RequestBody @Valid Authority auth, @PathVariable Long snsNumber) {

    String authList = parsingService.listParsing(auth.getSns().getContent());

    //contents -> parsing -> authList set
    if(authList!=null){
      auth.setAuthList(authList);
    }

    return authorityService.saveAuthority(auth);
  }

  /**
   * 권한 상세보기
   *
   * @return Authority
   */
  @RequestMapping(value = "/show/{authNumber}", method = RequestMethod.GET)
  @ResponseBody
  public Authority showAuthority(@PathVariable Long authNumber) {

    return authorityService.showAuthority(authNumber);

  }

  /**
   * 권한 수정
   *
   * @return Authority
   */
  @RequestMapping(value = "/update/{authNumber}", method = RequestMethod.PATCH)
  @ResponseBody
  public Authority updateAuthority(@PathVariable Long authNumber, @RequestBody @Valid Authority auth){
    return authorityService.updateAuthority(authNumber, auth);
  }


}
