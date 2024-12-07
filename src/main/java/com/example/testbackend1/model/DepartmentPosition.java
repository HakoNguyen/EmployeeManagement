package com.example.testbackend1.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "department_code", referencedColumnName = "departmentCode")
    Department department;

    @ManyToOne
    @JoinColumn(name = "position_code", referencedColumnName = "positionCode")
    Position position;
}
