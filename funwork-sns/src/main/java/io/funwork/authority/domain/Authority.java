package io.funwork.authority.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.funwork.sns.domain.Sns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by urosaria on 2015. 11. 5..
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="authority")
public class Authority implements Serializable{

  /* auth 일련번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long authNumber;

  /* 권한목록  */
  @Column(name="authList")
  private String authList;

  /* sns 고유번호 */
  @OneToOne
  @JoinColumn(name = "number")
  private Sns sns;


}
