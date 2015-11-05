package io.funwork.authority.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.funwork.sns.domain.Sns;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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

  /* 권한목록 @urosaria$dev */
  @NotNull
  @Column(name="authList")
  private String authList;

  /* sns 고유번호 */
  @ManyToOne
  @JoinColumn(name = "number")
  @JsonManagedReference
  private Sns sns;


}
