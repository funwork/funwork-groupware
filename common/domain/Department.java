package io.funwork.organ.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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
  private Set<Department> childDept = new HashSet<>();

  @NotNull
  private String useYn = "N";

  /**
   * 자식부서 추가 및 부모부서로 등록.
   *
   * @param department 부모부서
   */
  public void setParentDept(Department department) {
    if (isNotExistChildDepartment(department)) {
      this.parentDept = department;
      department.getChildDept().add(this);
    }
  }

  private boolean isNotExistChildDepartment(Department department) {
    return department != null && !department.getChildDept().contains(this);
  }
}
