package com.example.testbackend1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    @Id
    @Size(max = 3, message = "DptCode chỉ được tối đa 3 ký tự")
    String departmentCode; // mã phòng ban
    String departmentName;
    int foundingYear; // năm thành lập
    String status; // trạng thái

    @OneToMany(mappedBy = "department")
    List<DepartmentPosition> departmentPositions; // Danh sách các vị trí liên quan

    public @Size(max = 3, message = "DptCode chỉ được tối đa 3 ký tự") String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(@Size(max = 3, message = "DptCode chỉ được tối đa 3 ký tự") String departmentCode) {
        this.departmentCode = departmentCode;
    }


}
