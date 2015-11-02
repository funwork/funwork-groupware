package io.funwork.organ.domain.support;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

  protected Long deptCode;

  @NotNull
  protected String deptName;
  protected Long parentDeptCode;
  protected String useYn = "N";

}
