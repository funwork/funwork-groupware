package io.funwork.organ.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import io.funwork.FunworkOrgan;
import io.funwork.organ.domain.Department;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkOrgan.class)
public class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Test
  public void test_dept_save(){

    //given
    Department department = new Department();
    department.setDeptName("test1");

    //when
    department = departmentRepository.save(department);

    //then
    Department newDepartment = departmentRepository.findOne(department.getDeptCode());
    assertThat(newDepartment.getDeptName(), is(department.getDeptName()));
  }

  @Test
  @Transactional
  public void test_parent_child_dept_save(){

    //given
    Department parentDepartment = new Department();
    parentDepartment.setDeptName("test1");
    parentDepartment = departmentRepository.save(parentDepartment);

    Department childDepartment = new Department();
    childDepartment.setDeptName("test2");
    childDepartment = departmentRepository.save(childDepartment);

    //when
    childDepartment.setParentDept(parentDepartment);

    //then
    Department newParentDepartment = departmentRepository.findOne(parentDepartment.getDeptCode());
    Department newChildDepartment = departmentRepository.findOne(childDepartment.getDeptCode());

    assertThat(newParentDepartment.getChildDept().size(), is(1));
    assertThat(newChildDepartment.getParentDept().getDeptName(), is(parentDepartment.getDeptName()));
  }
}