package io.funwork.parsing.service;

import org.springframework.stereotype.Service;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by urosaria on 2015. 11. 11..
 */
@Service
public class ParsingService {

  /**
   * parsing list
   *
   * @param contents
   * @return
   */
  public String listParsing(String contents){
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
