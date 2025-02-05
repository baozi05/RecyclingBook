package org.example.baozi.recyclingbook.model.Entity.Student;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
public class Student {

    @TableId(value = "studentId", type = IdType.NONE)
    private String studentId; // 学号作为主键，数据库内已导入学号

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("campus")
    private String campus; // 校区: 朝晖/屏峰/莫干山

    @TableField("dormitory")
    private String dormitory; // 宿舍地址

    @TableField("payment_method")
    private String paymentMethod; // 默认收款方式

    @TableField("reputation_score")
    private Integer reputationScore; // 信誉分

    @TableField("status")
    private boolean status; // 状态 0:正常 1:限制使用

    @TableField("is_first_login")
    private Boolean isFirstLogin = true; // 是否首次登录
}

