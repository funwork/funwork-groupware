package io.funwork.organ.service;

import io.funwork.organ.domain.Department;
import io.funwork.organ.domain.support.DepartmentDto;
import io.funwork.organ.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrganizationService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * 부서등록.
   *
   * @param department 외부에서 넘어온 부서DTO
   * @return 저장된 부서정보
   */
  @Transactional
  public Department saveDepartment(DepartmentDto department) {
    Department newDepartment = departmentRepository.save(
        modelMapper.map(department, Department.class));
    if (isHaveParentDeptCode(department)) {
      newDepartment.setParentDept(departmentRepository.findOne(department.getParentDeptCode()));
    }
    return newDepartment;
  }

  /**
   * 부서조회.
   *
   * @param deptCode 조회할 부서코드
   * @return 부서
   */
  public Department showDepartment(Long deptCode) {
    return departmentRepository.findOne(deptCode);
  }

  /**
   * 부서수정.
   *
   * @param deptCode 수정대상인 부서 code
   * @param departmentDto 수정내용
   * @return 수정부서
   */
  @Transactional
  public Department updateDepartment(Long deptCode, DepartmentDto departmentDto) {

    Department department = showDepartment(deptCode);
    department.setDeptName(departmentDto.getDeptName());
    department.setUseYn(departmentDto.getUseYn());

    if (isHaveParentDeptCode(departmentDto)) {
      department.setParentDept(departmentRepository.findOne(departmentDto.getParentDeptCode()));
    }

    return department;
  }

  /**
   * 부서DTO가 부모키를 가지고 있는지 여부확인.
   * 업데이트나 등록시 부모부서를 등록하기 위해서 사용함
   *
   * @param department 부서DTO
   * @return true/false
   */
  private boolean isHaveParentDeptCode(DepartmentDto department) {
    return department.getParentDeptCode() != null;
  }
}
