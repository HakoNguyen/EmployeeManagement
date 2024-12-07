package com.example.testbackend1.dto;

import com.example.testbackend1.model.Employee;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    Employee employee;
}
