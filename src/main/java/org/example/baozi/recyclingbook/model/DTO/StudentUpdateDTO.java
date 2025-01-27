package org.example.baozi.recyclingbook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDTO {
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6,message = "密码至少需要6位")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",message = "密码必须是数字和字母的组合")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
