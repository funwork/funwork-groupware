package io.funwork.organ.domain.support;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DepartmentDto {

  protected Long deptCode;

  @NotNull
  protected String deptName;
  protected Long parentDeptCode;
  protected String useYn = "N";

}
