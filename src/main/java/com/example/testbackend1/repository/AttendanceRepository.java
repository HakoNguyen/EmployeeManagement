package com.example.testbackend1.repository;

import com.example.testbackend1.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    Optional<Attendance> findByEmployeeEmployeeId(String employeeId);
    Optional<Attendance> findById(Long id);
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByEmployeeEmployeeIdAndDate(String employeeId, LocalDate date); // Tìm chấm công theo mã nhân viên và ngày
    List<Attendance> findByEmployeeEmployeeIdAndDateBetween(String employeeId, LocalDate startDate, LocalDate endDate); // Tìm chấm công theo khoảng thời gian
}
