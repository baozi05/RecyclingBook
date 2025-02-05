package org.example.baozi.recyclingbook.model.Entity.Book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sealedbook")
public class SealedBook {
    @TableId(type = IdType.ASSIGN_ID)
    private Long s_bookId;

    private String s_bookName;

    private String s_ownerId;

    private Byte[] s_bookData;
}
