package org.example.baozi.recyclingbook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDTO {

    private String studentId;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 6,message = "密码至少需要6位")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",message = "密码必须是数字和字母的组合")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^\\d{11}$",message = "请输入正确的11位手机号")
    private String studentPhone;

    @NotBlank(message = "校区不能为空")
    private String studentCampus;

    @NotBlank(message = "宿舍地址不能为空")
    private String studentDormitory;

    @NotBlank(message = "收款方式不能为空")
    private String payment_method;
}
