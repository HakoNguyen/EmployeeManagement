package com.example.testbackend1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Nếu bạn muốn tự động sinh ID
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    LocalDate date; // ngay cham cong
    LocalDateTime checkIn; // check in
    LocalDateTime checkOut; // check out
}
