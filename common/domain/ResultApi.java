package io.funwork.organ.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultApi<T> implements Serializable{

  private HttpStatus code;
  private String msg;
  private T data;

  public ResultApi(HttpStatus code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ResultApi(HttpStatus code, T data) {
    this.code = code;
    this.data = data;
  }
}
