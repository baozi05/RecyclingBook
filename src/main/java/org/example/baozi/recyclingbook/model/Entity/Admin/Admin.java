package org.example.baozi.recyclingbook.model.Entity.Admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String account;
    private String password;
    private String role;
}
