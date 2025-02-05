package org.example.baozi.recyclingbook.ResponseMessage.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageVO {
    private Long id;
    private String senderId;
    private String content;
    private Boolean isRead;
    private LocalDateTime createTime;
}
