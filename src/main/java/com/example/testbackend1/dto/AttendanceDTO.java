package com.example.testbackend1.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceDTO {
    private Long id;
    private LocalDate check_in;
    private LocalDate check_out;
    private LocalDate date;
    private String employee_id;
    double totalHoursWorked; // tong so gio lam viec
}
