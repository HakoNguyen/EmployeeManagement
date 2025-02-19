package com.example.testbackend1.controller;

import com.example.testbackend1.dto.EmployeeAttendanceDTO;
import com.example.testbackend1.dto.SalaryDTO;
import com.example.testbackend1.model.Attendance;
import com.example.testbackend1.model.Employee;
import com.example.testbackend1.service.AttendanceService;
import com.example.testbackend1.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    // Tạo nhân viên
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Lấy tất cả nhân viên
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Lấy thông tin nhân viên theo ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Lấy danh sách chấm công
    @GetMapping("/{employeeId}/attendances")
    public ResponseEntity<List<Map<String, Object>>>
    // Tạo Map chứa các trường trả về
    getAttendancesByEmployee(@PathVariable String employeeId) {
        List<Attendance> attendances = attendanceService.getAttendancesByEmployee(employeeId);

        if (attendances == null || attendances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Chuyển đổi attendances từ danh sách sang Map
        List<Map<String, Object>> attendanceResponse = attendances.stream()
                .map(attendance -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    responseMap.put("date", attendance.getDate().toString());
                    responseMap.put("checkIn", attendance.getCheckIn());
                    responseMap.put("checkOut", attendance.getCheckOut());
                    return responseMap;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(attendanceResponse, HttpStatus.OK);
    }


    // Cập nhật thông tin nhân viên
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(employeeId, updatedEmployee);
    }
    @Transactional
    // Xóa nhân viên theo ID
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    // Tính lương hàng năm của nhân viên theo Employee ID và năm
    @GetMapping("/{employeeId}/salary/{year}")
    public Map<Integer, SalaryDTO> getAnnualSalary(@PathVariable String employeeId, @PathVariable int year) {
        return employeeService.calculateAnnualSalary(employeeId, year);
    }


    @GetMapping("/{employeeId}/salary/{month}/{year}")
    public SalaryDTO getMonthlySalary(@PathVariable String employeeId, @PathVariable int month, @PathVariable int year) {
        // Tính tổng số giờ làm việc trong tháng và năm
        double totalHoursWorked = employeeService.calculateTotalHoursWorked(employeeId, month, year);

        Employee employee = employeeService.getEmployeeById(employeeId);

        // Tính lương và trả về SalaryDTO
        return employeeService.getSalaryDTO(employeeId, employee, totalHoursWorked);
    }
}
