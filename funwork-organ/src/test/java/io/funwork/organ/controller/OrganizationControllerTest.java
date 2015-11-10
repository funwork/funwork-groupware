package io.funwork.organ.controller;

import com.jayway.restassured.RestAssured;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import io.funwork.FunworkOrgan;
import io.funwork.organ.domain.Department;
import io.funwork.organ.domain.support.DepartmentDto;
import io.funwork.organ.repository.DepartmentRepository;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkOrgan.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class OrganizationControllerTest {

  private static final String DEPT_PREFIX_URL = "/organ/department";
  private static final String CONTENT_TYPE = "application/json";

  @Autowired
  private DepartmentRepository departmentRepository;

  @Value("${local.server.port}")
  private int port;

  @Before
  public void setup() {
    RestAssured.port = port;
  }

  //@todo: 원래는 하나의 테스트코드는 하나의 테스트만 담당해야하나 전수개념으로 했음 차후 수정
  @Test
  @Transactional
  public void test_create_department_and_child_department() throws Exception {

    Department department = new Department();
    department.setDeptName("테스트1부서");
    department.setUseYn("Y");

    given()
      .contentType(CONTENT_TYPE)
      .body(department)
    .when()
      .post(DEPT_PREFIX_URL)
    .then()
      .statusCode(HttpStatus.SC_OK)
      .body(containsString(department.getDeptName()));

    DepartmentDto childDepartment = new DepartmentDto();
    childDepartment.setDeptName("테스트1-1부서");
    childDepartment.setUseYn("Y");
    childDepartment.setParentDeptCode(1L);

    given()
      .contentType(CONTENT_TYPE)
      .body(childDepartment)
    .when()
      .post(DEPT_PREFIX_URL)
    .then()
      .statusCode(HttpStatus.SC_OK)
      .body(containsString(childDepartment.getDeptName()));

    assertThat(departmentRepository.findOne(1L).getChildDept().size(), is(1));
  }
}