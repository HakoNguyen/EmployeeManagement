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
public class Position {
    @Id
    @Size(max = 1, message = "PstCode chỉ được tối đa 1 ký tự")
    String positionCode; // mã chức vụ
    String positionName;
    double basicSalary; // lương cơ bản

    public @Size(max = 1, message = "PstCode chỉ được tối đa 1 ký tự") String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(@Size(max = 1, message = "PstCode chỉ được tối đa 1 ký tự") String positionCode) {
        this.positionCode = positionCode;
    }

}
