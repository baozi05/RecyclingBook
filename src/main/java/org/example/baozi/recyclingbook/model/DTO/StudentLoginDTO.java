package org.example.baozi.recyclingbook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLoginDTO {
    @NotBlank(message = "学号不能为空")
    private String studentId;
    @NotBlank(message = "密码不能为空")
    private String password;
}
