package io.funwork.parsing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by urosaria on 2015. 11. 11..
 */
@RestController
@RequestMapping(value = "/parsing" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ParsingController {

  @RequestMapping(value = "/list" , method = RequestMethod.POST)
  @ResponseBody
  public String list(@RequestBody @Valid String contents){

  //패턴 @D[부서], @P[사용자ID], @G[그룹명]
  Pattern departPattern = Pattern.compile("(@P|@D|@G)\\[[^\\[\\]]+\\]");

  Matcher matcher = departPattern.matcher(contents);

    StringJoiner joiner = new StringJoiner(",");
    while(matcher.find()){
      joiner.add(matcher.group());
    }

    String joined = joiner.toString();

    return joined;
  }


}
