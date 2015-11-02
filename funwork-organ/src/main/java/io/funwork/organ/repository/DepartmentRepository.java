package io.funwork.organ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.funwork.organ.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
