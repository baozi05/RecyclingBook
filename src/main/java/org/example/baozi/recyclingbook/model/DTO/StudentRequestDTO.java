package org.example.baozi.recyclingbook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
    @NotBlank(message = "学号不能为空")
    private String studentId;

    @Min(value = 0,message = "信誉分最低为0")
    @Max(value = 100,message = "信誉分最高为100")
    private int newScore;

    private boolean status=true;
}
