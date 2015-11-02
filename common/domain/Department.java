package io.funwork.organ.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Department implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long deptCode;

  @NotNull
  public String deptName;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  @JsonManagedReference
  public Department parentDept;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  private Set<Department> childDept = new HashSet<Department>();

  @NotNull
  private String useYn = "N";

  public void addChildDept(Department department) {
    Assert.notNull(department);
    if (!getChildDept().contains(department)) getChildDept().add(department);
  }
}
