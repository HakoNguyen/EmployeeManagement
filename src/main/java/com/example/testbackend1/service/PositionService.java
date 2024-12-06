package com.example.testbackend1.service;

import com.example.testbackend1.model.Department;
import com.example.testbackend1.model.Position;
import com.example.testbackend1.repository.DepartmentRepository;
import com.example.testbackend1.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Tạo chức vụ
    public Position addPosition(Position position) {
        // Kiểm tra xem position có hợp lệ không
        if (position.getPositionCode() == null || position.getPositionName() == null) {
            throw new RuntimeException("Position code and name cannot be null");
        }

        // Nếu bạn không muốn yêu cầu departmentPositions, hãy bỏ qua kiểm tra này
        if (position.getDepartmentPositions() != null && position.getDepartmentPositions().isEmpty()) {
            // Bỏ qua kiểm tra này nếu bạn muốn cho phép thêm Position mà không cần DepartmentPosition
        }

        // Lưu vị trí
        return positionRepository.save(position);
    }
    // Lấy tất cả chức vụ
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    // Xóa chức vụ
    public void deletePosition(String positionCode) {
        if (!positionRepository.existsByPositionCode(positionCode)) {
            throw new RuntimeException("Position not found");
        }
        positionRepository.deleteByPositionCode(positionCode);
    }
}