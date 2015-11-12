package io.funwork.sns.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.funwork.authority.domain.Authority;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by urosaria on 2015. 10. 23..
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="sns")
public class Sns implements Serializable{

  /* sns 일련번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long number;

  /* 사용자 Id */
  @NotNull
  @Column(name="email")
  private String email;

  /* 부서코드 */
  //@NotNull
  @Column(name="deptCode")
  private long deptCode;

  /* 부서명 */
  //@NotNull
  @Column(name="deptName")
  private String deptName;

  /* 작성일 */
  @Column(name="cDate")
  private String cDate;

  /* 내용 */
  @Column(name="content")
  private String content;

  /* 사용여부 */
  @NotNull
  @Column(name="useYn")
  private String useYn="Y";

  //@OneToOne
  //@JoinColumn(name="number")
  //private Authority authority;

}
