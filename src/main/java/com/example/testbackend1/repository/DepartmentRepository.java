package com.example.testbackend1.repository;

import com.example.testbackend1.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentCode(String departmentCode);
=======
@NonNullApi // Khai bao rang moi gia tri duoc tham chieu se khong null
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findById(String id); // Tìm theo id
    // tim phong ban qua code
    Department findByDepartmentCode(String departmentCode);

    // kiem tra su ton tai cua phong ban
>>>>>>> origin/main
    boolean existsByDepartmentCode(String departmentCode);
    void deleteByDepartmentCode(String departmentCode);
}