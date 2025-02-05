package org.example.baozi.recyclingbook.model.DTO;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MessageDTO {
    @NotNull
    private String senderId;

    @NotNull
    private String receiverId;

    @NotBlank
    @Size(max = 1000)
    private String content;
}
