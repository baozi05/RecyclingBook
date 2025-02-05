package org.example.baozi.recyclingbook.model.Entity.Message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("messages")
public class Messages {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String senderId;
    private String receiverId;
    private String content;
    private Boolean isRead;
    private LocalDateTime createTime;
}
