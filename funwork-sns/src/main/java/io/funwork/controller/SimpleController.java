package io.funwork.controller;

import io.funwork.domain.SimpleTest;
import io.funwork.repository.SimpleTestRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테스트용 컨트롤러 제거예정.
 *
 * @author changhwaoh
 */
@RestController
@RequestMapping("/simple")
@Slf4j
public class SimpleController {

  @Autowired
  private SimpleTestRepository simpleTestRepository;

  /**
   * 샘플 제거예정.
   *
   * @return simple sample
   */
  @RequestMapping("/test")
  public String simpleTest() {
    log.debug("Test {}", ">_<");
    SimpleTest simpleTest = new SimpleTest();
    simpleTest.setTest("Test");
    simpleTestRepository.save(simpleTest);
    return "helloworld";
  }
}
