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


    // tao chuc vu
    public Position addPosition(Position position) {
        Long departmentId = position.getDepartment() != null ? position.getDepartment().getId() : null;

        if (departmentId == null ) {
            throw new RuntimeException("Department cannot be null");
        }
        return positionRepository.save(position);
    }


    // Lay tat ca chuc vu
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
    // xoa chuc vu
    public void deletePosition(String positionCode) {
        if (!positionRepository.existsByPositionCode(positionCode)) {
            throw new RuntimeException("Position not found");
        }
        positionRepository.deleteByPositionCode(positionCode);
    }

}
