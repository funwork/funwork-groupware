package io.funwork.sns.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by urosaria on 2015. 10. 23..
 */
@Data
@Entity
@Table(name="sns")
public class Sns {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private long id;

  @Column(name="memberId")
  private String memberId;

  @Column(name="cDate")
  private String cDate;

  @Column(name="content")
  private String content;

  @Column(name="status")
  private String status="A";

}
