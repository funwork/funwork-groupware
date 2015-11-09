package io.funwork.organ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.funwork.FunworkOrgan;
import io.funwork.organ.domain.Department;
import io.funwork.organ.domain.support.DepartmentDto;
import io.funwork.organ.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkOrgan.class)
public class OrganizationServiceTest {

  @Autowired
  private OrganizationService organizationService;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Test
  public void test_update_department() throws Exception {

    //given
    Department department = makeFixture();
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDeptCode(department.getDeptCode());
    departmentDto.setDeptName("테스트부서2");

    //when
    organizationService.updateDepartment(departmentDto.getDeptCode(), departmentDto);

    //then
    Department newDepartment = departmentRepository.findOne(department.getDeptCode());
    assertThat(newDepartment.getDeptName(), is(departmentDto.getDeptName()));
  }

  @Test
  public void test_update_department_parent() throws Exception{

    //given
    Department department = makeFixture();
    DepartmentDto childDepartmentDto = new DepartmentDto();
    childDepartmentDto.setDeptName("테스트부서1-1");
    childDepartmentDto.setUseYn("Y");

    Department childDepartment = organizationService.saveDepartment(childDepartmentDto);

    //when
    childDepartmentDto.setParentDeptCode(department.getDeptCode());
    Department newDepartment = organizationService.updateDepartment(childDepartment.getDeptCode(), childDepartmentDto);

    //then
    assertThat(newDepartment.getParentDept().getDeptName(), is(department.getDeptName()));
    assertThat(newDepartment.getParentDept().getChildDept().size(), is(1));
  }

  private Department makeFixture() {
    Department department = new Department();
    department.setDeptName("테스트부서1");
    department.setUseYn("Y");
    department = departmentRepository.save(department);
    return department;
  }


}