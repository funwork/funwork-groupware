package io.funwork.organ.controller;

import io.funwork.organ.domain.Department;
import io.funwork.organ.domain.ResultApi;
import io.funwork.organ.domain.support.DepartmentDto;
import io.funwork.organ.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/organ" ,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

  @Autowired
  private OrganizationService organizationService;

  /**
   * 부서생성.
   *
   * @param department 생성할 부서정보
   * @param result result
   * @return httpstatus, 생성된부서정보
   */
  @RequestMapping(value = "/department", method = RequestMethod.POST)
  public ResultApi<Department> createDepartment(@RequestBody @Valid DepartmentDto department,
                                                BindingResult result) {

    if (result.hasErrors()) {
      return new ResultApi<>(HttpStatus.BAD_REQUEST, "");
    }

    return new ResultApi<>(HttpStatus.CREATED, organizationService.saveDepartment(department));
  }

  @RequestMapping(value = "/department/{deptCode}", method = RequestMethod.GET)
  public ResultApi<Department> showDepartment(@PathVariable Long deptCode) {
    return new ResultApi<>(HttpStatus.OK, organizationService.showDepartment(deptCode));
  }

  @RequestMapping(value = "/department/{deptCode}", method = RequestMethod.PATCH)
  public ResultApi<Department> updateDepartment(@PathVariable Long deptCode,
                                                @RequestBody DepartmentDto departmentDto) {
    return new ResultApi<>(HttpStatus.OK,
        organizationService.updateDepartment(deptCode, departmentDto));
  }
}
