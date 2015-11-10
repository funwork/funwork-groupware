package io.funwork.organ.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Person implements Serializable {

  /* 사용자 이메일 */
  @Id
  private String email;

  /* 사용자 암호 */
  @NotNull
  @Size(min = 8, max = 20)
  private String passwd;

  /* 사용자 이름 */
  @NotNull
  private String name;

  /* 사용자 직책 */
  private String position;

  /* 사용자 직위 */
  private String office;

  /* 사용자 보안등급 */
  @NotNull
  private SecurityGrade securityGrade = SecurityGrade.NORMAL;

  /* 사용자 인증키 */
  private String authKey;

}
