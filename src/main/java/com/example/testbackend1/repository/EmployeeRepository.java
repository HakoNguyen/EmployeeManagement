package com.example.testbackend1.repository;

import com.example.testbackend1.model.Department;
import com.example.testbackend1.model.Employee;
import com.example.testbackend1.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeId(String employeeId);
    boolean existsByEmployeeId(String employeeId);
    void deleteByEmployeeId(String employeeId);
    long countByDepartmentAndPosition(Department department, Position position);
}