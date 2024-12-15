package com.example.testbackend1.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @Column(unique = true, nullable = false) // Đảm bảo employeeId là duy nhất và không null
    // Khóa chính
    String employeeId;
    String name;
    String address; // địa chỉ
    String email; // email
    LocalDate dateOfBirth; // ngày sinh
    LocalDate dateOfJoining; // ngày vào làm việc


    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    Position position;


}
