package com.example.testbackend1.service;

import com.example.testbackend1.dto.SalaryDTO;
import com.example.testbackend1.model.Attendance;
import com.example.testbackend1.model.Department;
import com.example.testbackend1.model.Employee;
import com.example.testbackend1.model.Position;
import com.example.testbackend1.repository.EmployeeRepository;
import com.example.testbackend1.repository.DepartmentRepository;
import com.example.testbackend1.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private AttendanceService attendanceService;
<<<<<<< HEAD

    // Thêm nhân viên mới
    public Employee addEmployee(Employee employee) {
        // Lấy departmentCode và positionCode từ đối tượng
        String departmentCode = employee.getDepartment() != null ? employee.getDepartment().getDepartmentCode() : null;
        String positionCode = employee.getPosition() != null ? employee.getPosition().getPositionCode() : null;
=======
    

    // Thêm nhân viên mới
    public Employee addEmployee(Employee employee) {
        // Lấy departmentId và positionId từ đối tượng
        String departmentId = employee.getDepartment() != null ? employee.getDepartment().getId() : null;
        String positionId = employee.getPosition() != null ? employee.getPosition().getId() : null;
>>>>>>> origin/main

        if (departmentCode == null || positionCode == null) {
            throw new RuntimeException("Department or Position cannot be null");
        }

        // Tìm kiếm department và position từ cơ sở dữ liệu theo code
        Department existingDepartment = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new RuntimeException("Department not found with code: " + departmentCode));

<<<<<<< HEAD
        Position existingPosition = positionRepository.findByPositionCode(positionCode)
                .orElseThrow(() -> new RuntimeException("Position not found with code: " + positionCode));
=======
        Position existingPosition = positionRepository.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Position not found with ID: " + positionId));

        // Lấy departmentCode và positionCode
        String departmentCode = existingDepartment.getDepartmentCode();
        String positionCode = existingPosition.getPositionCode();
        double basicSalary = existingPosition.getBasicSalary();
>>>>>>> origin/main

        // Tính toán employeeId dựa trên departmentCode và positionCode
        long count = employeeRepository.countByDepartmentAndPosition(existingDepartment, existingPosition);
        String employeeId = departmentCode + positionCode + String.format("%04d", count + 1);

        // Gán employeeId cho nhân viên
        employee.setEmployeeId(employeeId);

        // Gán department và position cho nhân viên
        employee.setDepartment(existingDepartment);
        employee.setPosition(existingPosition);

        // Lưu nhân viên vào cơ sở dữ liệu
        return employeeRepository.save(employee);
}
    // Lấy tất cả nhân viên
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Lấy thông tin nhân viên theo Employee ID
    public Employee getEmployeeById(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
    }

    // Cập nhật thông tin nhân viên
    public Employee updateEmployee(String employeeId, Employee updatedEmployee) {
        // Tìm nhân viên hiện tại trong cơ sở dữ liệu
        Employee existingEmployee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setPosition(updatedEmployee.getPosition());

        // Lưu lại thay đổi
        return employeeRepository.save(existingEmployee);
    }

    // Xóa nhân viên theo Employee ID
    public void deleteEmployee(String employeeId) {
        if (!employeeRepository.existsByEmployeeId(employeeId)) {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }
        // Xóa nhân viên
        employeeRepository.deleteByEmployeeId(employeeId);
    }

    // Tính lương hàng năm của nhân viên
    public Map<Integer, SalaryDTO> calculateAnnualSalary(String employeeId, int year) {
        Map<Integer, SalaryDTO> monthlySalaries = new HashMap<>();

        // Lặp qua các tháng trong năm và tính lương mỗi tháng
        for (int month = 1; month <= 12; month++) {
            LocalDate startDate = LocalDate.of(year, month, 1); // Ngày bắt đầu tháng
            LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // Ngày kết thúc tháng

            // Tính tổng số giờ làm việc trong tháng
            double totalHoursWorked = attendanceService.calculateTotalHoursWorked(employeeId, startDate, endDate);

            // Lấy thông tin nhân viên
            Employee employee = employeeRepository.findByEmployeeId(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            // Tính toán lương cho tháng
            SalaryDTO salaryDTO = getSalaryDTO(employeeId, employee, totalHoursWorked);
            monthlySalaries.put(month, salaryDTO);
        }

        return monthlySalaries;
    }

    public double calculateTotalHoursWorked(String employeeId, int month, int year) {
        // Lấy danh sách chấm công của nhân viên trong tháng và năm đã cho
        List<Attendance> attendances = attendanceService.getAttendancesByEmployee(employeeId, month, year);
        double totalHours = 0;

        // Tính tổng số giờ làm việc từ danh sách chấm công
        for (Attendance attendance : attendances) {
            if (attendance.getCheckIn() != null && attendance.getCheckOut() != null) {
                Duration duration = Duration.between(attendance.getCheckIn(), attendance.getCheckOut());
                totalHours += duration.toHours();
            }
        }

        return totalHours;
    }

    // Tính toán lương cho từng tháng
    public SalaryDTO getSalaryDTO(String employeeId, Employee employee, double totalHoursWorked) {
<<<<<<< HEAD
        Position position = employee.getPosition();
        double basicSalary = position.getBasicSalary(); // Lấy lương cơ bản từ Position
=======
        Position position = employee.getPosition(); // Lấy Position từ Employee
        double basicSalary = position.getBasicSalary(); // Lấy basicSalary từ Position
>>>>>>> origin/main
        double hourlyRate = basicSalary / 40; // Lương theo giờ (40 giờ là tiêu chuẩn)

        double salary = basicSalary;
        double overtimeSalary = 0;

        // Tính lương làm thêm nếu tổng giờ làm việc vượt quá 40 giờ
        if (totalHoursWorked > 40) {
            double overtimeHours = totalHoursWorked - 40;
            overtimeSalary = overtimeHours * hourlyRate * 1.5; // Lương làm thêm tính theo hệ số 1.5
            salary += overtimeSalary;
        }

        // Trả về đối tượng SalaryDTO với thông tin lương của nhân viên
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setEmployeeId(employeeId);// Thiết lập basicSalary
        salaryDTO.setOvertimeSalary(overtimeSalary);
        salaryDTO.setTotalSalary(salary);
        return salaryDTO;
    }
}