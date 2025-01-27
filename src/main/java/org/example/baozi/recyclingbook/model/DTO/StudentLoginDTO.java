package org.example.baozi.recyclingbook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLoginDTO {
    @NotBlank(message = "用户名不能为空")
    private String studentId;
    @NotBlank(message = "密码不能为空")
    private String password;
}
