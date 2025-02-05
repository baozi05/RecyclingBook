package org.example.baozi.recyclingbook.model.Entity.Book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("recycablebook")
public class RecyclableBook {
    @TableId(type = IdType.ASSIGN_ID)
    private Long r_bookId;

    private String r_bookName;

    private String printingEdition;

    private String publisher;

    private String r_ownerId;

    private byte[] r_bookData;
}
