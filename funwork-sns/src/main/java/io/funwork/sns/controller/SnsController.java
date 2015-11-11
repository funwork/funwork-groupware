package io.funwork.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import io.funwork.sns.repository.SnsRepository;
import io.funwork.sns.service.SnsService;
import lombok.extern.slf4j.Slf4j;
import io.funwork.sns.domain.Sns;

/**
 * Created by urosaria on 2015. 10. 23..
 */
@RestController
@RequestMapping(value = "/sns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SnsController {

  @Autowired
  private SnsService snsService;

  /**
   * sns list (권한테이블과 조인예정 임시)
   *
   * @return list
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public List<Sns> list() {

    return snsService.listSns();
  }

  /**
   * sns insert
   *
   * @return list
   */
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  @ResponseBody
  public List<Sns> insert(@RequestBody @Valid Sns sns) {

    return snsService.saveSns(sns);

  }

  /**
   * sns show
   *
   * @return sns
   */
  @RequestMapping(value = "/show/{number}", method = RequestMethod.GET)
  @ResponseBody
  public Sns showSns(@PathVariable Long number) {
    return snsService.showSns(number);
  }

  /**
   * sns update
   *
   * @return list
   */
  @RequestMapping(value = "/update/{number}", method = RequestMethod.PATCH)
  @ResponseBody
  public Sns update(@PathVariable Long number, @RequestBody @Valid Sns sns) {

    return snsService.updateSns(number, sns);
  }

}
